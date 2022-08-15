package org.dev.barsukov.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.converter.TransactionConverter;
import org.dev.barsukov.service.TransactionService;
import org.dev.barsukov.service.crud.CrudTransactionService;
import org.dev.barsukov.service.dto.TransactionDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Slf4j
@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final CrudTransactionService crud;
    private final TransactionConverter converter;
    @Qualifier("statusesCache")
    private final Map<Long, String> statusesCache;

    private final static int MONTH_SECONDS = 2630000;
    private final static String PROCESSING = "processing";
    private final static String COMPLETED = "completed";
    private final static String DEFAULT_HISTORY_FILE_DIR = "user-history-files";

    public List<TransactionDto> getAllTransactions(String apiKey, String symbol, String incomeType, Long startTime, Long endTime, Integer limit) {
        Timestamp st = null;
        Timestamp et = null;
        if (startTime == null || endTime == null) {
            log.warn("Orders date is incorrect. Set to 3 months ago and now");
            Instant now = Instant.now();
            et = new Timestamp(now.toEpochMilli());
            st = new Timestamp(now.minusSeconds(3 * MONTH_SECONDS).toEpochMilli());
        } else {
            st = new Timestamp(startTime);
            et = new Timestamp(endTime);
        }
        if (limit == null) {
            limit = 500;
            log.warn("Limit is null, set to default: 500");
        }
        if (limit > 1000) {
            limit = 1000;
            log.warn("Limit is too big, set to max: 1000");
        }
        return converter.toDto(crud.findAllBy(apiKey,
                symbol,
                incomeType,
                st,
                et,
                limit));
    }

    public List<TransactionDto> save(List<TransactionDto> dto, String apiKey) {
        return crud.save(dto, apiKey);
    }

    @Override
    public Long asyncHistoryRunCreateFileTask(String apiKey, Long startTime, Long endTime) throws IOException {
        Long id = generateRandomReadableId();
        String path = System.getenv("EMULATOR_HISTORY_DIR_ABS") != null
                      ? System.getenv("EMULATOR_HISTORY_DIR_ABS")
                      : DEFAULT_HISTORY_FILE_DIR;
        try {
            Files.createDirectory(Paths.get(path));
        } catch (FileAlreadyExistsException ignored) {}
        File file = new File(path + "/" + id + ".json");
        file.createNewFile();
        statusesCache.put(id, PROCESSING);
        new Thread(() -> createHistoryFile(apiKey, startTime, endTime, file, id)).start();
        return id;
    }

    private void createHistoryFile(String apiKey, Long startTime, Long endTime, File file, Long id)  {
        List<TransactionDto> allTransactions = getAllTransactions(apiKey, null, null, startTime, endTime, 1000);
        ObjectMapper mapper = new ObjectMapper();
        try (FileWriter writer = new FileWriter(file.getAbsoluteFile())) {
            writer.append("[");
            for (int i = 0; i < allTransactions.size(); i++) {
                if (i != 0) writer.append(",\n");
                writeToFile(mapper, writer, allTransactions.get(i));
            }
            writer.append("]");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        statusesCache.put(id, COMPLETED);
    }

    private void writeToFile(ObjectMapper mapper, FileWriter writer, TransactionDto transaction) {
        try {
            writer.append(mapper.writeValueAsString(transaction));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
	public void delete(String apiKey) {
		crud.delete(apiKey);
	}

    private Long generateRandomReadableId() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("[^\\d]", "");
        return Long.parseLong(uuid.substring(uuid.length()/2));
    }
}
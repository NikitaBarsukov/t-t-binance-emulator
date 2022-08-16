package org.dev.barsukov.service;


import org.dev.barsukov.service.dto.TransactionDto;

import java.io.IOException;
import java.util.List;

public interface TransactionService {

    List<TransactionDto> getAllTransactions(String sessionId, String symbol, String incomeType, Long startTime, Long endTime, Integer limit);

    List<TransactionDto> save(List<TransactionDto> dto, String apiKey);

    /**
     * Creates file and runs task for file create
     *
     * @return file id
     * @throws IOException
     */
    Long asyncHistoryRunCreateFileTask(String apiKey, Long startTime, Long endTime) throws IOException;

    void delete(String apiKey);
}

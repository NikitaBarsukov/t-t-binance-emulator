package org.dev.barsukov.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.converter.TransactionConverter;
import org.dev.barsukov.service.TransactionService;
import org.dev.barsukov.service.crud.CrudTransactionService;
import org.dev.barsukov.service.dto.TransactionDto;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final CrudTransactionService crud;
    private final TransactionConverter converter;

    private final static int MONTH_SECONDS = 2630000;

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
	public void delete(String apiKey) {
		crud.delete(apiKey);
	}
}
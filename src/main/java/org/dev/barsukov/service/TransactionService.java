package org.dev.barsukov.service;


import org.dev.barsukov.service.dto.TransactionDto;

import java.util.List;

public interface TransactionService {

    List<TransactionDto> getAllTransactions(String sessionId, String symbol, String incomeType, Long startTime, Long endTime, Integer limit);

    List<TransactionDto> save(List<TransactionDto> dto, String apiKey);
}

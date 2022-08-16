package org.dev.barsukov.service.crud;

import org.dev.barsukov.entity.TransactionEntity;
import org.dev.barsukov.service.dto.TransactionDto;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.List;

public interface CrudTransactionService {

    TransactionDto findOne(Long id);

    void delete(Long id);

    void delete(String apiKey);

    List<TransactionEntity> findAllBy(String apiKey,
                                               String symbol,
                                               String incomeType,
                                               Timestamp startTime,
                                               Timestamp endTime,
                                               Integer limit);

    TransactionDto save(TransactionDto dto, String apiKey);

    TransactionDto save(TransactionEntity entity);


    List<TransactionDto> save(List<TransactionDto> dto, String apiKey);
}
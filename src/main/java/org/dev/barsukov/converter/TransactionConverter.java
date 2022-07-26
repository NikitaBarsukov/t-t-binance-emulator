package org.dev.barsukov.converter;

import org.dev.barsukov.entity.TransactionEntity;
import org.dev.barsukov.service.dto.TransactionDto;

import java.util.List;


public interface TransactionConverter {

    TransactionDto toDto(TransactionEntity entity);

    TransactionEntity fromDto (TransactionDto dto, String apiKey);

    List<TransactionDto> toDto(List<TransactionEntity> trades);
}

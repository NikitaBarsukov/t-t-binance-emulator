package org.dev.barsukov.service.impl.converter;

import org.dev.barsukov.converter.TransactionConverter;
import org.dev.barsukov.entity.TransactionEntity;
import org.dev.barsukov.service.dto.TransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionConverterImpl implements TransactionConverter {

    @Override
    public TransactionDto toDto(TransactionEntity entity) {
        return TransactionDto.builder()
                .id(entity.getId())
                .symbol(entity.getSymbol())
                .incomeType(entity.getIncomeType())
                .income(entity.getIncome())
                .asset(entity.getAsset())
                .info(entity.getInfo())
                .time(entity.getTime())
                .tranId(entity.getTranId())
                .tradeId(entity.getTradeId())
                .build();
    }

    @Override
    public TransactionEntity fromDto(TransactionDto dto, String apiKey) {
        return new TransactionEntity(
                null,
                dto.getSymbol(),
                dto.getIncomeType(),
                dto.getIncome(),
                dto.getAsset(),
                dto.getInfo(),
                dto.getTime(),
                dto.getTranId(),
                dto.getTradeId(),
                apiKey
        );
    }

    @Override
    public List<TransactionDto> toDto(List<TransactionEntity> transactions) {
        return transactions.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

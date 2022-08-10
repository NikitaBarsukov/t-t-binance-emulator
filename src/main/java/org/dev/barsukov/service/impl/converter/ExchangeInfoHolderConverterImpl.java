package org.dev.barsukov.service.impl.converter;

import org.dev.barsukov.converter.ExchangeInfoHolderConverter;
import org.dev.barsukov.entity.ExchangeInfoHolderEntity;
import org.dev.barsukov.service.dto.ExchangeInfoHolderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeInfoHolderConverterImpl implements ExchangeInfoHolderConverter {

    @Override
    public ExchangeInfoHolderDto toDto(ExchangeInfoHolderEntity entity) {
        return ExchangeInfoHolderDto.builder()
                .build();
    }

    @Override
    public ExchangeInfoHolderEntity fromDto(ExchangeInfoHolderDto dto, String apiKey) {
        return new ExchangeInfoHolderEntity(

        );
    }

    @Override
    public List<ExchangeInfoHolderDto> toDto(List<ExchangeInfoHolderEntity> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

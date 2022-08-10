package org.dev.barsukov.converter;

import org.dev.barsukov.entity.ExchangeInfoHolderEntity;
import org.dev.barsukov.service.dto.ExchangeInfoHolderDto;

import java.util.List;


public interface ExchangeInfoHolderConverter {

    ExchangeInfoHolderDto toDto(ExchangeInfoHolderEntity entity);

    ExchangeInfoHolderEntity fromDto (ExchangeInfoHolderDto dto, String apiKey);

    List<ExchangeInfoHolderDto> toDto(List<ExchangeInfoHolderEntity> trades);
}

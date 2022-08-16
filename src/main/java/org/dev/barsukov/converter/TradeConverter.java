package org.dev.barsukov.converter;

import org.dev.barsukov.entity.TradeEntity;
import org.dev.barsukov.service.dto.TradeDto;

import java.util.List;


public interface TradeConverter {

    TradeDto toDto(TradeEntity entity);

    TradeEntity fromDto (TradeDto dto, String apiKey);

    List<TradeDto> toDto(List<TradeEntity> trades);
}

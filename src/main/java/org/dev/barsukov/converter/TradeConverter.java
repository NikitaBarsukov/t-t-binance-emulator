package org.dev.barsukov.converter;

import org.dev.barsukov.entity.TradeEntity;
import org.dev.barsukov.service.dto.TradeDto;


public interface TradeConverter {

    TradeDto toDto(TradeEntity entity);

    TradeEntity fromDto (TradeDto dto);
}
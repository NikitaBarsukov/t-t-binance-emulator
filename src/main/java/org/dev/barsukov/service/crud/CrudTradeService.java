package org.dev.barsukov.service.crud;

import com.fasterxml.jackson.databind.JsonNode;
import org.dev.barsukov.entity.TradeEntity;
import org.dev.barsukov.service.dto.TradeDto;

public interface CrudTradeService {

    TradeDto findOne(Long id);

    void delete(Long id);

    TradeDto save(TradeDto dto, String apiKey);

	TradeDto save(TradeEntity entity);
}
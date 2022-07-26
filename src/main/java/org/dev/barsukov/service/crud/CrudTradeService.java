package org.dev.barsukov.service.crud;

import org.dev.barsukov.entity.TradeEntity;
import org.dev.barsukov.service.dto.TradeDto;

import java.sql.Timestamp;
import java.util.List;

public interface CrudTradeService {

    TradeDto findOne(Long id);

    void delete(Long id);

    TradeDto save(TradeDto dto, String apiKey);

	TradeDto save(TradeEntity entity);

    List<TradeEntity> findAllBy(String sessionId, String symbol, Long fromId, Timestamp startTime, Timestamp endTime, Integer limit);
}
package org.dev.barsukov.service.crud;

import org.dev.barsukov.service.dto.TradeDto;

public interface CrudTradeService {

    TradeDto findOne(Long id);

    void delete(Long id);

    TradeDto save(TradeDto dto, String apiKey);
}
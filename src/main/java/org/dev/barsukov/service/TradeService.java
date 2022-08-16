package org.dev.barsukov.service;


import org.dev.barsukov.service.dto.TradeDto;

import java.util.List;

public interface TradeService {


    List<TradeDto> getAllOrders(String sessionId, String symbol, Long fromId, Long startTime, Long endTime, Integer limit);
}

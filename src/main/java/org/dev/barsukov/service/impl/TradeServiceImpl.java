package org.dev.barsukov.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.converter.TradeConverter;
import org.dev.barsukov.service.TradeService;
import org.dev.barsukov.service.crud.CrudTradeService;
import org.dev.barsukov.service.dto.TradeDto;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class TradeServiceImpl implements TradeService {
    private final CrudTradeService crud;
    private final TradeConverter converter;

    @Override
    public List<TradeDto> getAllOrders(String sessionId, String symbol, Long fromId, Long startTime, Long endTime, Integer limit) {
        Timestamp st = null;
        Timestamp et = null;
        if (startTime == null || endTime == null) {
            log.warn("Orders date is incorrect. Set to one week");
            Instant now = Instant.now();
            et = new Timestamp(now.toEpochMilli());
            st = new Timestamp(now.minusSeconds(604800).toEpochMilli());
        } else {
            st = new Timestamp(startTime);
            et = new Timestamp(endTime);
        }
        if (limit == null) {
            limit = 500;
            log.warn("Limit is null, set to default: 500");
        }
        if (limit > 1000) {
            limit = 1000;
            log.warn("Limit is too big, set to max: 1000");
        }
       return converter.toDto(crud.findAllBy(sessionId,
                symbol,
                fromId,
                st,
                et,
                limit));
    }
}

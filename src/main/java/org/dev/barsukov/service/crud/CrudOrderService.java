package org.dev.barsukov.service.crud;

import org.dev.barsukov.service.dto.OrderDto;

import java.util.List;

public interface CrudOrderService {

    OrderDto findOne(Long id);

    OrderDto findBy(String sessionId, String symbol, Long orderId, String clientOrderId);

    void deleteById(Long id);

    OrderDto save(OrderDto dto);

    List<OrderDto> findAllBy(String sessionId, String symbol, Long orderId, Long startTime, Long endTime, Integer limit);

}
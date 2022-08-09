package org.dev.barsukov.service;

import org.dev.barsukov.service.dto.OrderDto;

public interface OrderService {

	OrderDto getOrder(String sessionId, String symbol, Long orderId, String clientOrderId);

	OrderDto createOrder(OrderDto dto);
}

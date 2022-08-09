package org.dev.barsukov.service.impl;

import lombok.AllArgsConstructor;
import org.dev.barsukov.service.FailOrderHolderService;
import org.dev.barsukov.service.OrderService;
import org.dev.barsukov.service.crud.CrudOrderService;
import org.dev.barsukov.service.dto.OrderDto;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final CrudOrderService crudOrderService;
	private final FailOrderHolderService failsService;

	@Override
	public OrderDto getOrder(String sessionId, String symbol, Long orderId, String clientOrderId) {
		return crudOrderService.findBy(sessionId,
				symbol,
				orderId,
				clientOrderId);
	}

	@Override
	public OrderDto createOrder(OrderDto dto) {
		failsService.сheckActiveFails(dto.getSessionId());
		return crudOrderService.save(dto);
	}
}

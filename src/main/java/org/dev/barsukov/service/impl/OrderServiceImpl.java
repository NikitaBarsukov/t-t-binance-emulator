package org.dev.barsukov.service.impl;

import lombok.AllArgsConstructor;
import org.dev.barsukov.converter.FailOrderConverter;
import org.dev.barsukov.entity.FailOrderEntity;
import org.dev.barsukov.service.FailOrderHolderService;
import org.dev.barsukov.service.OrderService;
import org.dev.barsukov.service.crud.CrudOrderService;
import org.dev.barsukov.service.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final CrudOrderService crudOrderService;
	private final FailOrderHolderService failsService;
	private final FailOrderConverter foConverter;

	@Override
	public OrderDto getOrder(String sessionId, String symbol, Long orderId, String clientOrderId) {
		return crudOrderService.findBy(sessionId,
				symbol,
				orderId,
				clientOrderId);
	}

	@Override
	public Object createOrder(OrderDto dto) {
		List<FailOrderEntity> failOrderEntities = failsService.getActiveFailsBySymbol(dto.getSymbol(), dto.getSessionId());
		if (failOrderEntities.size() == 0) {
			return crudOrderService.save(dto);
		} else {
			return failOrderEntities.stream()
					.findFirst()
					.map(foConverter::toDto)
					.get()
					.getPayload();
		}
	}
}

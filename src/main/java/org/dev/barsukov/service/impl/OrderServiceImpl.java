package org.dev.barsukov.service.impl;

import lombok.AllArgsConstructor;
import org.dev.barsukov.converter.FailConverter;
import org.dev.barsukov.entity.FailEntity;
import org.dev.barsukov.service.FailHolderService;
import org.dev.barsukov.service.OrderService;
import org.dev.barsukov.service.crud.CrudOrderService;
import org.dev.barsukov.service.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final CrudOrderService crudOrderService;
	private final FailHolderService failsService;
	private final FailConverter foConverter;

	@Override
	public OrderDto getOrder(String sessionId, String symbol, Long orderId, String clientOrderId) {
		return crudOrderService.findBy(sessionId,
				symbol,
				orderId,
				clientOrderId);
	}

	@Override
	public Object createOrder(OrderDto dto) {
		List<FailEntity> failOrderEntities = failsService.getActiveFailsBySymbolAndEndPoint(dto.getSymbol(), dto.getSessionId(), "order");
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

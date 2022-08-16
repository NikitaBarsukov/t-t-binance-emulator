package org.dev.barsukov.converter;

import org.dev.barsukov.entity.OrderEntity;
import org.dev.barsukov.service.dto.OrderDto;

import java.util.List;


public interface OrderConverter {

    OrderDto toDto(OrderEntity entity);

    OrderEntity fromDto (OrderDto dto);

    List<OrderDto> toDto(List<OrderEntity> allByBySessionId);
}

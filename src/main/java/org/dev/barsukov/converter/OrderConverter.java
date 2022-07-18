package org.dev.barsukov.converter;

import org.dev.barsukov.entity.OrderEntity;
import org.dev.barsukov.service.dto.OrderDto;


public interface OrderConverter {

    OrderDto toDto(OrderEntity entity);

    OrderEntity fromDto (OrderDto dto);
}

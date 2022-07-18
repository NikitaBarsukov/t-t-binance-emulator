package org.dev.barsukov.service.crud;

import org.dev.barsukov.service.dto.OrderDto;

public interface CrudOrderService {

    OrderDto findOne(Long id);

    void delete(Long id);

    OrderDto save(OrderDto dto);
}
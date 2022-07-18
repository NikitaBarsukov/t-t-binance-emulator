package org.dev.barsukov.service.impl.crud;

import lombok.AllArgsConstructor;
import org.dev.barsukov.converter.OrderConverter;
import org.dev.barsukov.exception.NoSuchOrderException;
import org.dev.barsukov.repository.OrderRepository;
import org.dev.barsukov.service.crud.CrudOrderService;
import org.dev.barsukov.service.dto.OrderDto;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CrudOrderServiceImpl implements CrudOrderService {
    private final OrderRepository repository;
    private final OrderConverter converter;

    @Override
    public OrderDto findOne(Long id) {
        return converter.toDto(repository.findById(id).orElseThrow(NoSuchOrderException::new));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public OrderDto save(OrderDto dto) {
        return converter.toDto(repository.save(converter.fromDto(dto)));
    }

//    https://stackoverflow.com/questions/32441919/how-return-error-message-in-spring-mvc-controller
}

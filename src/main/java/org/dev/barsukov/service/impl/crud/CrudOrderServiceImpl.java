package org.dev.barsukov.service.impl.crud;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.converter.OrderConverter;
import org.dev.barsukov.exception.NoSuchOrderException;
import org.dev.barsukov.repository.OrderRepository;
import org.dev.barsukov.service.crud.CrudOrderService;
import org.dev.barsukov.service.dto.OrderDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Slf4j
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
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public OrderDto save(OrderDto dto) {
        return converter.toDto(repository.save(converter.fromDto(dto)));
    }

    @Override
    public List<OrderDto> findAllBy(String sessionId, String symbol, Long orderId, Long startTime, Long endTime, Integer limit) {
        if (startTime == null || endTime == null) {
            log.warn("Orders date is incorrect. Set to one week");
            Instant now = Instant.now();
            endTime = now.toEpochMilli();
            startTime = now.minusSeconds(604800).toEpochMilli();
        }
        if (limit == null) {
            limit = 500;
            log.warn("Limit is null, set to default: 500");
        }
        if (limit > 1000) {
            limit = 1000;
            log.warn("Limit is too big, set to max: 1000");
        }
        return converter.toDto(repository.findAllByUrlParams(sessionId,
                symbol,
                orderId,
                new Timestamp(startTime),
                new Timestamp(endTime),
                PageRequest.of(0, limit)));
    }


    @Override
    public OrderDto findBy(String sessionId, String symbol, Long orderId, String origClientOrderId) {
        if (sessionId == null) {
            log.error("SessionId is null can not find orders");
            throw new NoSuchOrderException();
        }
        if (orderId == null && origClientOrderId == null) {
            log.error("orderId with ClientOrderId is null. Can not find any order. For all orders use /allOrder");
            throw new NoSuchOrderException();
        }
        return converter.toDto(repository.findOrderByUrlParams(sessionId, symbol, orderId, origClientOrderId));
    }
}

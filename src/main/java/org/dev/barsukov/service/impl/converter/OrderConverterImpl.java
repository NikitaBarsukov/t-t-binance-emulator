package org.dev.barsukov.service.impl.converter;

import org.dev.barsukov.converter.OrderConverter;
import org.dev.barsukov.entity.OrderEntity;
import org.dev.barsukov.service.dto.OrderDto;
import org.springframework.stereotype.Service;

@Service
public class OrderConverterImpl implements OrderConverter {

    @Override
    public OrderDto toDto(OrderEntity entity) {
        return  new OrderDto(
            entity.getId(),
            entity.getSessionId(),
            entity.getAvgPrice(),
            entity.getClientOrderId(),
            entity.getCumQuote(),
            entity.getExecutedQty(),
            entity.getOrderId(),
            entity.getOrigQty(),
            entity.getOrigType(),
            entity.getPrice(),
            entity.getReduceOnly(),
            entity.getSide(),
            entity.getPositionSide(),
            entity.getStatus(),
            entity.getStopPrice(),
            entity.getClosePosition(),
            entity.getSymbol(),
            entity.getTime(),
            entity.getTimeInForce(),
            entity.getType(),
            entity.getActivatePrice(),
            entity.getPriceRate(),
            entity.getUpdateTime(),
            entity.getWorkingType(),
            entity.getPriceProtect()
        );
    }

    @Override
    public OrderEntity fromDto(OrderDto dto) {
        return OrderEntity.builder().id(dto.getId())
                .sessionId(dto.getSessionId())
                .avgPrice(dto.getAvgPrice())
                .clientOrderId(dto.getClientOrderId())
                .cumQuote(dto.getCumQuote())
                .executedQty(dto.getExecutedQty())
                .orderId(dto.getOrderId())
                .origQty(dto.getOrigQty())
                .origType(dto.getOrigType())
                .price(dto.getPrice())
                .reduceOnly(dto.getReduceOnly())
                .side(dto.getSide())
                .positionSide(dto.getPositionSide())
                .status(dto.getStatus())
                .stopPrice(dto.getStopPrice())
                .closePosition(dto.getClosePosition())
                .symbol(dto.getSymbol())
                .time(dto.getTime())
                .timeInForce(dto.getTimeInForce())
                .type(dto.getType())
                .activatePrice(dto.getActivatePrice())
                .priceRate(dto.getPriceRate())
                .updateTime(dto.getUpdateTime())
                .workingType(dto.getWorkingType())
                .priceProtect(dto.getPriceProtect())
                .build();
    }
}

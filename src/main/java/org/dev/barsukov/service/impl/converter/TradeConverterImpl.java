package org.dev.barsukov.service.impl.converter;

import org.dev.barsukov.converter.TradeConverter;
import org.dev.barsukov.entity.TradeEntity;
import org.dev.barsukov.service.dto.TradeDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeConverterImpl implements TradeConverter {

    @Override
    public TradeDto toDto(TradeEntity entity) {
        return  TradeDto.builder()
                .id(entity.getId())
                .buyer(entity.getBuyer())
                .commission(entity.getCommission())
                .commissionAsset(entity.getCommissionAsset())
                .maker(entity.getMaker())
                .orderId(entity.getOrderId())
                .price(entity.getPrice())
                .qty(entity.getQty())
                .quoteQty(entity.getQuoteQty())
                .realizedPnl(entity.getRealizedPnl())
                .side(entity.getSide())
                .positionSide(entity.getPositionSide())
                .symbol(entity.getSymbol())
                .time(entity.getTime())
                .build();
    }

    @Override
    public TradeEntity fromDto(TradeDto dto, String apiKey) {
        return new TradeEntity(
                dto.getId(),
                apiKey,
                dto.getBuyer(),
                dto.getCommission(),
                dto.getCommissionAsset(),
                dto.getMaker(),
                dto.getOrderId(),
                dto.getPrice(),
                dto.getQty(),
                dto.getQuoteQty(),
                dto.getRealizedPnl(),
                dto.getSide(),
                dto.getPositionSide(),
                dto.getSymbol(),
                dto.getTime()
        );
    }

    @Override
    public List<TradeDto> toDto(List<TradeEntity> trades) {
        return trades.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

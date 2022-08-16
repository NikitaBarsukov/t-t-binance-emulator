package org.dev.barsukov.service.impl.converter;

import org.dev.barsukov.converter.PriceConverter;
import org.dev.barsukov.entity.PriceEntity;
import org.dev.barsukov.service.dto.PriceDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceConverterImpl implements PriceConverter {

    @Override
    public PriceDto toDto(PriceEntity entity) {
        return PriceDto.builder()
                .symbol(entity.getSymbol())
                .price(entity.getPrice())
                .time(entity.getTime())
                .build();
    }

    @Override
    public PriceEntity fromDto(PriceDto dto, String apiKey) {
        return new PriceEntity(
                dto.getId(),
                dto.getSymbol(),
                apiKey,
                dto.getPrice(),
                dto.getTime()
        );
    }

    @Override
    public List<PriceDto> toDto(List<PriceEntity> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PriceEntity> fromDto(List<PriceDto> dto, String apiKey) {
        return dto.stream().map(x-> fromDto(x, apiKey)).toList();
    }
}

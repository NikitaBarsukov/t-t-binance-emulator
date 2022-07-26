package org.dev.barsukov.service.impl.converter;

import org.dev.barsukov.converter.LeverageConverter;
import org.dev.barsukov.entity.LeverageEntity;
import org.dev.barsukov.service.dto.LeverageDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeverageConverterImpl implements LeverageConverter {

    @Override
    public LeverageDto toDto(LeverageEntity entity) {
        return LeverageDto.builder()
                .id(null)
                .leverage(entity.getLeverage())
                .maxNotionalValue(entity.getMaxNotionalValue())
                .symbol(entity.getSymbol())
                .build();
    }

    @Override
    public LeverageEntity fromDto(LeverageDto dto, String apiKey) {
        return new LeverageEntity(
                dto.getId(),
                dto.getLeverage(),
                dto.getMaxNotionalValue(),
                dto.getSymbol()
        );
    }

    @Override
    public List<LeverageDto> toDto(List<LeverageEntity> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
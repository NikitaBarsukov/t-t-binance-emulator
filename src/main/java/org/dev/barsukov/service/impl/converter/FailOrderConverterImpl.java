package org.dev.barsukov.service.impl.converter;

import org.dev.barsukov.converter.FailOrderConverter;
import org.dev.barsukov.entity.FailOrderEntity;
import org.dev.barsukov.service.dto.FailOrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FailOrderConverterImpl implements FailOrderConverter {

    @Override
    public FailOrderDto toDto(FailOrderEntity entity) {
        return FailOrderDto.builder()
                .id(entity.getId())
                .symbol(entity.getSymbol())
                .error(entity.getError())
                .apiKey(entity.getApiKey())
                .isActive(entity.getIsActive())
                .build();
    }

    @Override
    public FailOrderEntity fromDto(FailOrderDto dto, String apiKey) {
        return new FailOrderEntity(
                dto.getId(),
                dto.getSymbol(),
                dto.getError(),
                apiKey,
                dto.getIsActive()
        );
    }

    @Override
    public List<FailOrderDto> toDto(List<FailOrderEntity> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

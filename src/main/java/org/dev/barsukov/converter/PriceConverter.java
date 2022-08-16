package org.dev.barsukov.converter;

import org.dev.barsukov.entity.PriceEntity;
import org.dev.barsukov.service.dto.PriceDto;

import java.util.List;


public interface PriceConverter {

    PriceDto toDto(PriceEntity entity);

    PriceEntity fromDto (PriceDto dto, String apiKey);

    List<PriceDto> toDto(List<PriceEntity> trades);

    List<PriceEntity> fromDto(List<PriceDto> dto, String apiKey);
}

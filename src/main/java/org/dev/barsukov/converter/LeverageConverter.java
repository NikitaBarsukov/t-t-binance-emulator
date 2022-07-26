package org.dev.barsukov.converter;

import org.dev.barsukov.entity.LeverageEntity;
import org.dev.barsukov.service.dto.LeverageDto;

import java.util.List;


public interface LeverageConverter {

    LeverageDto toDto(LeverageEntity entity);

    LeverageEntity fromDto (LeverageDto dto, String apiKey);

    List<LeverageDto> toDto(List<LeverageEntity> trades);
}

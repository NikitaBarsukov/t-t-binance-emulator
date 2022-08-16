package org.dev.barsukov.converter;

import org.dev.barsukov.entity.FailEntity;
import org.dev.barsukov.service.dto.FailDto;

import java.util.List;


public interface FailConverter {

    FailDto toDto(FailEntity entity);

    FailEntity fromDto (FailDto dto);

    List<FailDto> toDto(List<FailEntity> trades);
}

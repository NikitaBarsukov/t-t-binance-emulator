package org.dev.barsukov.converter;

import org.dev.barsukov.entity.FailOrderEntity;
import org.dev.barsukov.service.dto.FailOrderDto;

import java.util.List;


public interface FailOrderConverter {

    FailOrderDto toDto(FailOrderEntity entity);

    FailOrderEntity fromDto (FailOrderDto dto);

    List<FailOrderDto> toDto(List<FailOrderEntity> trades);
}

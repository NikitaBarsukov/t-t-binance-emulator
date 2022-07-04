package org.dev.barsukov.converter;

import org.dev.barsukov.entity.KeyEntity;
import org.dev.barsukov.service.dto.KeyDto;

import java.util.List;

public interface KeyConverter {

    KeyDto toDto(KeyEntity entity);

    KeyEntity fromDto (KeyDto dto);

    List<KeyDto> toDto(List<KeyEntity> all);
}

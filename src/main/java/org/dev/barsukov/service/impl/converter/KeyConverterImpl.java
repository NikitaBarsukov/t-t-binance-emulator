package org.dev.barsukov.service.impl.converter;

import org.dev.barsukov.converter.KeyConverter;
import org.dev.barsukov.entity.KeyEntity;
import org.dev.barsukov.service.dto.KeyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyConverterImpl implements KeyConverter {
    @Override
    public KeyDto toDto(KeyEntity entity) {
        return null;
    }

    @Override
    public List<KeyDto> toDto(List<KeyEntity> all) {
        return null;
    }

    @Override
    public KeyEntity fromDto(KeyDto dto) {
        return new KeyEntity(dto.getKey(), dto.getSecret());
    }
}

package org.dev.barsukov.service.impl.converter;

import org.dev.barsukov.converter.ListenKeyConverter;
import org.dev.barsukov.entity.ListenKeyEntity;
import org.dev.barsukov.service.dto.ListenKeyDto;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class ListenKeyConverterImpl implements ListenKeyConverter {
    private int HOUR_SEC = 3_600;

    @Override
    public ListenKeyDto toDto(ListenKeyEntity entity) {
        return new ListenKeyDto(entity.getId(), entity.getApiKey(), entity.getApiSecret(), entity.getListenKey());
    }

    @Override
    public ListenKeyEntity fromDto(ListenKeyDto dto) {
        return new ListenKeyEntity(dto.getId(),
                dto.getApiKey(),
                dto.getSecretKey(),
                dto.getListenKey(),
                Timestamp.from(Instant.now()),
                Timestamp.from(Instant.now().plusSeconds(HOUR_SEC))
        );
    }
}

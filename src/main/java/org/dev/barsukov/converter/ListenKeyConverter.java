package org.dev.barsukov.converter;

import org.dev.barsukov.entity.ListenKeyEntity;
import org.dev.barsukov.service.dto.ListenKeyDto;

public interface ListenKeyConverter {

    ListenKeyDto toDto(ListenKeyEntity entity);

    ListenKeyEntity fromDto (ListenKeyDto dto);

}

package org.dev.barsukov.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.dev.barsukov.entity.EventHolderEntity;
import org.dev.barsukov.service.dto.EventHolderDto;

public interface EventHolderConverter {

    EventHolderDto toDto(EventHolderEntity entity) throws JsonProcessingException;

    EventHolderEntity fromDto (EventHolderDto dto);

}

package org.dev.barsukov.service.impl.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.converter.EventHolderConverter;
import org.dev.barsukov.entity.EventHolderEntity;
import org.dev.barsukov.service.dto.EventHolderDto;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventHolderConverterImpl implements EventHolderConverter {

    @Override
    public EventHolderDto toDto(EventHolderEntity entity) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonEvent = null;
        try {
            jsonEvent = mapper.readTree(entity.getEvent());
        } catch (JsonProcessingException e) {
            log.error("Can not cast Event to JSON.", e);
        }
        return new EventHolderDto(
                entity.getId(),
                jsonEvent,
                entity.getSessionId(),
                entity.getApiKey(),
                entity.getSecretKey(),
                entity.getSendOrder()
        );
    }

    @Override
    public EventHolderEntity fromDto(EventHolderDto dto) {
        return new EventHolderEntity(
                dto.getId(),
                dto.getSendOrder(),
                dto.getSessionId(),
                dto.getApiKey(),
                dto.getSecretKey(),
                dto.getEvent().toPrettyString()
        );
    }
}

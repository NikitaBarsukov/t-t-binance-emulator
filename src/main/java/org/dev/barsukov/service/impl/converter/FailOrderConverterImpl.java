package org.dev.barsukov.service.impl.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.converter.FailOrderConverter;
import org.dev.barsukov.entity.FailOrderEntity;
import org.dev.barsukov.service.dto.FailOrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FailOrderConverterImpl implements FailOrderConverter {

    @Override
    public FailOrderDto toDto(FailOrderEntity entity) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode orderFail = null;
        try {
            orderFail = mapper.readTree(entity.getPayload());
        } catch (JsonProcessingException e) {
            log.error("Can not cast Event to JSON.", e);
        }
        return FailOrderDto.builder()
                .id(entity.getId())
                .symbol(entity.getSymbol())
                .payload(orderFail)
                .apiKey(entity.getApiKey())
                .isActive(entity.getIsActive())
                .build();
    }

    @Override
    public FailOrderEntity fromDto(FailOrderDto dto) {
        return new FailOrderEntity(
                dto.getId(),
                dto.getSymbol(),
                dto.getPayload().toPrettyString(),
                dto.getApiKey(),
                dto.getIsActive() == null || dto.getIsActive()
        );
    }

    @Override
    public List<FailOrderDto> toDto(List<FailOrderEntity> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

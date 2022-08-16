package org.dev.barsukov.service.impl.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.converter.FailConverter;
import org.dev.barsukov.entity.FailEntity;
import org.dev.barsukov.service.dto.FailDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FailConverterImpl implements FailConverter {

    @Override
    public FailDto toDto(FailEntity entity) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode fail = null;
        try {
            fail = mapper.readTree(entity.getPayload());
        } catch (JsonProcessingException e) {
            log.error("Can not cast fail payload to JSON.", e);
        }
        return FailDto.builder()
                .id(entity.getId())
                .symbol(entity.getSymbol())
                .endpoint(entity.getEndpoint())
                .payload(fail)
                .apiKey(entity.getApiKey())
                .isActive(entity.getIsActive())
                .build();
    }

    @Override
    public FailEntity fromDto(FailDto dto) {
        return new FailEntity(
                dto.getId(),
                dto.getSymbol(),
                dto.getEndpoint(),
                dto.getPayload().toPrettyString(),
                dto.getApiKey(),
                dto.getIsActive() == null || dto.getIsActive()
        );
    }

    @Override
    public List<FailDto> toDto(List<FailEntity> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

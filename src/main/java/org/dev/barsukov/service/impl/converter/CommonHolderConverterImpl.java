package org.dev.barsukov.service.impl.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.converter.CommonHolderConverter;
import org.dev.barsukov.entity.CommonHolderEntity;
import org.dev.barsukov.service.dto.CommonHolderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommonHolderConverterImpl implements CommonHolderConverter {

    @Override
    public CommonHolderDto toDto(CommonHolderEntity entity) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode payload = null;
        try {
            payload = mapper.readTree(entity.getPayload());
        } catch (JsonProcessingException e) {
            log.error("Can not cast payload to JSON.", e);
        }
        return CommonHolderDto.builder()
                .id(entity.getId())
                .endpoint(entity.getEndpoint())
                .payload(payload)
                .apiKey(entity.getApiKey())
                .isActive(entity.getIsActive())
                .build();
    }

    @Override
    public CommonHolderEntity fromDto(CommonHolderDto dto) {
        return new CommonHolderEntity(
                dto.getId(),
                dto.getEndpoint(),
                dto.getPayload().toPrettyString(),
                dto.getApiKey(),
                dto.getIsActive() == null || dto.getIsActive()
        );
    }

    @Override
    public List<CommonHolderDto> toDto(List<CommonHolderEntity> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

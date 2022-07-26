package org.dev.barsukov.service.impl.converter;

import org.dev.barsukov.converter.CrudFailConverter;
import org.dev.barsukov.entity.CrudFailEntity;
import org.dev.barsukov.service.dto.CrudFailDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrudFailConverterImpl implements CrudFailConverter {

    @Override
    public CrudFailDto toDto(CrudFailEntity entity) {
        return CrudFailDto.builder()
                .build();
    }

    @Override
    public CrudFailEntity fromDto(CrudFailDto dto, String apiKey) {
        return new CrudFailEntity(

        );
    }

    @Override
    public List<CrudFailDto> toDto(List<CrudFailEntity> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

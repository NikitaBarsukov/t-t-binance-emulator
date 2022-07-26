package org.dev.barsukov.converter;

import org.dev.barsukov.entity.CrudFailEntity;
import org.dev.barsukov.service.dto.CrudFailDto;

import java.util.List;


public interface CrudFailConverter {

    CrudFailDto toDto(CrudFailEntity entity);

    CrudFailEntity fromDto (CrudFailDto dto, String apiKey);

    List<CrudFailDto> toDto(List<CrudFailEntity> trades);
}

package org.dev.barsukov.service.crud;

import org.dev.barsukov.entity.CrudFailEntity;
import org.dev.barsukov.service.dto.CrudFailDto;

import java.sql.Timestamp;
import java.util.List;

public interface CrudCrudFailService {

    CrudFailDto findOne(Long id);

    void delete(Long id);

    CrudFailDto save(CrudFailDto dto, String apiKey);

    CrudFailDto save(CrudFailEntity entity);

    List<CrudFailDto> save(List<CrudFailDto> dto, String apiKey);
}
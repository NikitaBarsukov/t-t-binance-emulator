package org.dev.barsukov.service.crud;

import org.dev.barsukov.entity.LeverageEntity;
import org.dev.barsukov.service.dto.LeverageDto;

import java.sql.Timestamp;
import java.util.List;

public interface CrudLeverageService {

    LeverageDto findOne(Long id);

    void delete(Long id);

    LeverageDto save(LeverageDto dto, String apiKey);

    LeverageDto save(LeverageEntity entity);

    List<LeverageDto> save(List<LeverageDto> dto, String apiKey);
}
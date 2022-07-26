package org.dev.barsukov.service.crud;

import org.dev.barsukov.entity.PriceEntity;
import org.dev.barsukov.service.dto.PriceDto;

import java.util.List;

public interface CrudPriceService {

    PriceDto findOne(Long id);

    void delete(Long id);

    PriceDto save(PriceDto dto, String apiKey);

    PriceDto save(PriceEntity entity);

    List<PriceEntity> save(List<PriceEntity> entities);

    List<PriceEntity> getAllBySymbol(String apiKey, String symbol);
}
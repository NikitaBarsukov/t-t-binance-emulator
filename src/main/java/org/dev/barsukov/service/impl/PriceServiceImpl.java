package org.dev.barsukov.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.converter.PriceConverter;
import org.dev.barsukov.entity.PriceEntity;
import org.dev.barsukov.service.PriceService;
import org.dev.barsukov.service.crud.CrudPriceService;
import org.dev.barsukov.service.dto.PriceDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {
    private final CrudPriceService crud;
    private final PriceConverter converter;

    @Override
    public List<PriceDto> save(List<PriceDto> dto, String apiKey) {
        List<PriceEntity> entities = converter.fromDto(dto, apiKey);
        return converter.toDto(crud.save(entities));
    }

    @Override
    public List<PriceDto> getAll(String apiKey, String symbol) {
        return converter.toDto(crud.getAllBySymbol(apiKey, symbol));
    }
}
package org.dev.barsukov.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.converter.FailOrderConverter;
import org.dev.barsukov.entity.FailOrderEntity;
import org.dev.barsukov.service.FailOrderHolderService;
import org.dev.barsukov.service.crud.CrudFailOrderService;
import org.dev.barsukov.service.dto.FailOrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
public class FailOrderServiceImpl implements FailOrderHolderService {
    private final CrudFailOrderService crud;
    private final FailOrderConverter converter;

	@Override
	public List<FailOrderDto> getAll() {
		return converter.toDto(crud.findAll());
	}

    @Override
    public Optional<FailOrderDto> get(Long id) {
        return crud.findOne(id).map(converter::toDto);
    }

    @Override
    public FailOrderDto addFailOrder(FailOrderDto dto) {
        return crud.save(converter.fromDto(dto));
    }

    @Override
    public FailOrderDto updateFailOrder(FailOrderDto dto) {
        FailOrderEntity entity = converter.fromDto(dto);
        return  converter.toDto(crud.update(entity));
    }

    @Override
    public void deleteFailOrder(Long id) {
        crud.delete(id);
    }

    @Override
    public List<FailOrderEntity> getActiveFailsBySymbol(String symbol, String sessionId) {
        return crud.findAllByApiKeyAndSymbol(symbol, sessionId);
    }
}
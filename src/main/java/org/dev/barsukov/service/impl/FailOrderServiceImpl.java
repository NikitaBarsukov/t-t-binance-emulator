package org.dev.barsukov.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.converter.FailOrderConverter;
import org.dev.barsukov.service.FailOrderHolderService;
import org.dev.barsukov.service.crud.CrudFailOrderService;
import org.dev.barsukov.service.dto.FailOrderDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class FailOrderServiceImpl implements FailOrderHolderService {
    private final CrudFailOrderService crud;
    private final FailOrderConverter converter;

    @Override
    public FailOrderDto addFailForOrder(FailOrderDto dto) {
        return crud.save(converter.fromDto(dto));
    }

	@Override
	public List<FailOrderDto> getAll() {
		return converter.toDto(crud.findAll());
	}
}
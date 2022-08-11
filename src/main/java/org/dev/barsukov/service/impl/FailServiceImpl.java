package org.dev.barsukov.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.converter.FailConverter;
import org.dev.barsukov.entity.FailEntity;
import org.dev.barsukov.service.FailHolderService;
import org.dev.barsukov.service.crud.CrudFailService;
import org.dev.barsukov.service.dto.FailDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
public class FailServiceImpl implements FailHolderService {
    private final CrudFailService crud;
    private final FailConverter converter;

	@Override
	public List<FailDto> getAll() {
		return converter.toDto(crud.findAll());
	}

    @Override
    public Optional<FailDto> get(Long id) {
        return crud.findOne(id).map(converter::toDto);
    }

    @Override
    public FailDto addFail(FailDto dto) {
        return crud.save(converter.fromDto(dto));
    }

    @Override
    public FailDto updateFail(FailDto dto) {
        FailEntity entity = converter.fromDto(dto);
        return  converter.toDto(crud.update(entity));
    }

    @Override
    public void deleteFail(Long id) {
        crud.delete(id);
    }

    @Override
    public List<FailEntity> getActiveFailsBySymbolAndEndPoint(String symbol, String sessionId, String endpoint) {
        return crud.findAllByApiKeyAndSymbolAndEndpoint(symbol, sessionId, endpoint);
    }
}
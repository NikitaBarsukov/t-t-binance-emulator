package org.dev.barsukov.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.converter.FailConverter;
import org.dev.barsukov.entity.FailEntity;
import org.dev.barsukov.service.FailService;
import org.dev.barsukov.service.crud.CrudFailService;
import org.dev.barsukov.service.dto.FailDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
public class FailServiceImpl implements FailService {
    private final CrudFailService crud;
    private final FailConverter converter;

	@Override
	public List<FailDto> getAll(String endpoint) {
		return converter.toDto(crud.findAll(endpoint));
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
        return  converter.toDto(crud.update(converter.fromDto(dto)));
    }

    @Override
    public void deleteFail(Long id) {
        crud.delete(id);
    }

    @Override
    public List<FailEntity> getActiveFailsBySymbolAndEndPoint(String symbol, String sessionId, String endpoint) {
        return crud.findAllByApiKeyAndSymbolAndEndpoint(symbol, sessionId, endpoint);
    }

    @Override
    public List<FailEntity> getActiveFailsByEndPoint(String apiKey, String endpoint) {
        return crud.findAllByApiKeyAndSymbolAndEndpoint(apiKey, endpoint);
    }
}
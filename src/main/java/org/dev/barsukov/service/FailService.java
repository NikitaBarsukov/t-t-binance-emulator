package org.dev.barsukov.service;


import org.dev.barsukov.entity.FailEntity;
import org.dev.barsukov.service.dto.FailDto;

import java.util.List;
import java.util.Optional;

public interface FailService {

	FailDto addFail(FailDto dto);

	List<FailDto> getAll(String endpoint);

	List<FailEntity> getActiveFailsBySymbolAndEndPoint(String symbol, String sessionId, String endpoint);

	List<FailEntity> getActiveFailsByEndPoint(String apiKey, String endpoint);

    Optional<FailDto> get(Long id);

	FailDto updateFail(FailDto dto);

	void deleteFail(Long id);
}

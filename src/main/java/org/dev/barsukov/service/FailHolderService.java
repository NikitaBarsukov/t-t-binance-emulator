package org.dev.barsukov.service;


import org.dev.barsukov.entity.FailEntity;
import org.dev.barsukov.service.dto.FailDto;

import java.util.List;
import java.util.Optional;

public interface FailHolderService {

	FailDto addFail(FailDto dto);

	List<FailDto> getAll();

	List<FailEntity> getActiveFailsBySymbolAndEndPoint(String symbol, String sessionId, String endpoint);

    Optional<FailDto> get(Long id);

	FailDto updateFail(FailDto dto);

	void deleteFail(Long id);
}

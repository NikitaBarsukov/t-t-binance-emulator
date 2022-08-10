package org.dev.barsukov.service;


import org.dev.barsukov.entity.FailOrderEntity;
import org.dev.barsukov.service.dto.FailOrderDto;

import java.util.List;
import java.util.Optional;

public interface FailOrderHolderService {

	FailOrderDto addFailOrder(FailOrderDto dto);

	List<FailOrderDto> getAll();

	List<FailOrderEntity> getActiveFailsBySymbol(String symbol, String sessionId);

    Optional<FailOrderDto> get(Long id);

	FailOrderDto updateFailOrder(FailOrderDto dto);

	void deleteFailOrder(Long id);
}

package org.dev.barsukov.service;


import org.dev.barsukov.service.dto.FailOrderDto;

import java.util.List;

public interface FailOrderHolderService {

	FailOrderDto addFailForOrder(FailOrderDto dto);

	List<FailOrderDto> getAll();

	void сheckActiveFails(String sessionId);

}

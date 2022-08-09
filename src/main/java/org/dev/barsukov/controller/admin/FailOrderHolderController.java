package org.dev.barsukov.controller.admin;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.FailOrderHolderService;
import org.dev.barsukov.service.dto.FailOrderDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/admin/fail")
public class FailOrderHolderController {
	private final FailOrderHolderService service;

	@ApiOperation(value = "Gets all fails for orders by user apiKey.")
	@GetMapping()
	public List<FailOrderDto> getFailHolders(@RequestHeader("X-MBX-APIKEY") String apiKey) {
		return service.getAll();
	}

	@ApiOperation(value = "Creates an fail messages on next user order with same symbol.")
	@PostMapping()
	public FailOrderDto addFailForOrder(@RequestHeader("X-MBX-APIKEY") String apiKey,
										@RequestBody FailOrderDto dto) {
		dto.setApiKey(apiKey);
		return service.addFailForOrder(dto);
	}
	//crud
}


package org.dev.barsukov.controller.admin;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.FailOrderHolderService;
import org.dev.barsukov.service.dto.FailOrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@ApiOperation(value = "Get failHolder by id.")
	@GetMapping()
	public List<FailOrderDto> getFailHolders(@RequestHeader("X-MBX-APIKEY") String apiKey) {
		return service.getAll();
	}


	@ApiOperation(value = "Gets all failHolders for orders by user apiKey.")
	@GetMapping("/{id}")
	public ResponseEntity<FailOrderDto> getFailHolder(@RequestHeader("X-MBX-APIKEY") String apiKey, @PathVariable("id") Long id) {
		return ResponseEntity.of(service.get(id));
	}

	@ApiOperation(value = "Creates an fail messages on next user order with same symbol.")
	@PostMapping()
	public FailOrderDto addFailOrder(@RequestHeader("X-MBX-APIKEY") String apiKey,
										@RequestBody FailOrderDto dto) {
		dto.setApiKey(apiKey);
		return service.addFailOrder(dto);
	}

	@ApiOperation(value = "Creates an fail messages on next user order with same symbol.")
	@PutMapping("/{id}")
	public FailOrderDto updateFailForOrder(@RequestHeader("X-MBX-APIKEY") String apiKey,
										@RequestBody FailOrderDto dto) {
		dto.setApiKey(apiKey);
		return service.updateFailOrder(dto);
	}

	@ApiOperation(value = "Creates an fail messages on next user order with same symbol.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteFailForOrder(@RequestHeader("X-MBX-APIKEY") String apiKey,
										   @PathVariable("id") Long id) {
		service.deleteFailOrder(id);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}
}
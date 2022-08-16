package org.dev.barsukov.controller.admin;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.FailService;
import org.dev.barsukov.service.dto.FailDto;
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
public class FailHolderController {
	private final FailService service;

	@ApiOperation(value = "Get failHolders by endpoint.")
	@GetMapping()
	public List<FailDto> getFailHolders(@RequestHeader("X-MBX-APIKEY") String apiKey,
										String endpoint) {
		return service.getAll(endpoint);
	}

	@ApiOperation(value = "Gets failHolder by id.")
	@GetMapping("/{id}")
	public ResponseEntity<FailDto> getFailHolder(@RequestHeader("X-MBX-APIKEY") String apiKey,
												 @PathVariable("id") Long id) {
		return ResponseEntity.of(service.get(id));
	}

	@ApiOperation(value = "Create failHolder message on endpoint")
	@PostMapping()
	public FailDto addFail(@RequestHeader("X-MBX-APIKEY") String apiKey,
						   @RequestBody FailDto dto) {
		dto.setApiKey(apiKey);
		return service.addFail(dto);
	}

	@ApiOperation(value = "Update failHolder on endpoint")
	@PutMapping("/{id}")
	public FailDto updateFailForOrder(@RequestHeader("X-MBX-APIKEY") String apiKey,
									  @PathVariable("id") Long id,
									  @RequestBody FailDto dto) {
		dto.setApiKey(apiKey);
		dto.setId(id);
		return service.updateFail(dto);
	}

	@ApiOperation(value = "Delete failHolder on endpoint")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteFailForOrder(@RequestHeader("X-MBX-APIKEY") String apiKey,
													 @PathVariable("id") Long id) {
		service.deleteFail(id);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}
}
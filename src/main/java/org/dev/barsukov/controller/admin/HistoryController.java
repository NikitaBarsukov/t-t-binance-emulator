package org.dev.barsukov.controller.admin;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.EventService;
import org.dev.barsukov.service.TransactionService;
import org.dev.barsukov.service.dto.TransactionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/admin/history")
public class HistoryController {
    private final TransactionService service;

    @ApiOperation(value = "Creates an order. If the WS is open puts it in there by listenkey.")
    @PostMapping()
    public ResponseEntity<Object> createOrder(@RequestHeader("X-MBX-APIKEY") String apiKey,  @RequestBody List<TransactionDto> dto) {
        return ResponseEntity.ok(service.save(dto, apiKey));
    }
}

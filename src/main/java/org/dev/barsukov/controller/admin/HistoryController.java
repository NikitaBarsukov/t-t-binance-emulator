package org.dev.barsukov.controller.admin;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.TransactionService;
import org.dev.barsukov.service.dto.TransactionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @ApiOperation(value = "Creates an user transactions")
    @PostMapping()
    public ResponseEntity<Object> createHistory(@RequestHeader("X-MBX-APIKEY") String apiKey,  @RequestBody List<TransactionDto> dto) {
        return ResponseEntity.ok(service.save(dto, apiKey));
    }

    @ApiOperation(value = "Delete All user transactions")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestHeader("X-MBX-APIKEY") String apiKey) {
        return ResponseEntity.ok(null);
    }
}

package org.dev.barsukov.controller.admin;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.PriceService;
import org.dev.barsukov.service.dto.PriceDto;
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
@RequestMapping("/admin/price")
public class AdminPriceController {
    private final PriceService service;

    @ApiOperation(value = "Creates an prices for symbol")
    @PostMapping()
    public ResponseEntity<Object> createPrice(@RequestHeader("X-MBX-APIKEY") String apiKey,  @RequestBody List<PriceDto> dto) {
        return ResponseEntity.ok(service.save(dto, apiKey));
    }
}

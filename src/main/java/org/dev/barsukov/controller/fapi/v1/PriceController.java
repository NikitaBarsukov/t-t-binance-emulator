package org.dev.barsukov.controller.fapi.v1;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.PriceService;
import org.dev.barsukov.service.dto.PriceDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/fapi/v1/ticker/price")
public class PriceController {
  private final PriceService service;

    @ApiOperation(value = "Get ticker price")
    @GetMapping()
    public List<PriceDto> getPrice(@RequestHeader("X-MBX-APIKEY") String apiKey,
                                     @RequestParam(required = false) String symbol) {
      return service.getAll(apiKey, symbol);
    }
}

package org.dev.barsukov.controller.fapi.v2;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.LeverageService;
import org.dev.barsukov.service.dto.LeverageDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/fapi/v2/positionRisk")
public class LeverageController {

  private final LeverageService service;

    @ApiOperation(value = "Creates an order. If the WS is open puts it in there by listenkey.")
    @GetMapping()
    public List<LeverageDto> getIncome(@RequestHeader("X-MBX-APIKEY") String apiKey,
                                              @RequestParam(required = false) String symbol,
                                              @RequestParam(required = false) Long recvWindow,
                                              @RequestParam(required = false) Long timestamp) {
      return null;
    }
}


package org.dev.barsukov.controller.fapi.v1;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.FailOrderService;
import org.dev.barsukov.service.dto.FailOrderDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/fapi/v1/income")
public class FailOrderController {

  private final FailOrderService service;

    @ApiOperation(value = "Creates an order. If the WS is open puts it in there by listenkey.")
    @GetMapping()
    public List<FailOrderDto> getIncome(@RequestHeader("X-MBX-APIKEY") String apiKey,
                                              @RequestParam(required = false) String symbol,
                                              @RequestParam(required = false) String incomeType,
                                              @RequestParam(required = false) Long startTime,
                                              @RequestParam(required = false) Long endTime,
                                              @RequestParam(required = false) Integer limit,
                                              @RequestParam(required = false) Long recvWindow,
                                              @RequestParam(required = false) Long timestamp) {
      return null;
    }
}


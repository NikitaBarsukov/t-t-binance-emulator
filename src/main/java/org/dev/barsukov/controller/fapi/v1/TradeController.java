package org.dev.barsukov.controller.fapi.v1;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.EventService;
import org.dev.barsukov.service.TradeService;
import org.dev.barsukov.service.crud.CrudOrderService;
import org.dev.barsukov.service.crud.CrudTradeService;
import org.dev.barsukov.service.dto.OrderDto;
import org.dev.barsukov.service.dto.TradeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/fapi/v1/userTrades")
public class TradeController {
    private final TradeService service;

    @ApiOperation(value = "Gets trades by sessionId and symbol.")
    @GetMapping()
    public List<TradeDto> getOrder(@RequestHeader("X-MBX-APIKEY") String sessionId,
                                   @RequestParam(required = false) String symbol,
                                   @RequestParam(required = false) Long fromId, //If orderId is set, it will get orders >= that orderId. Otherwise most recent orders are returned.
                                   @RequestParam(required = false) Long recvWindow, //Binance doc doesn't contain any info about this prop
                                   @RequestParam(required = false) Long startTime,
                                   @RequestParam(required = false) Long endTime,
                                   @RequestParam(required = false) Integer limit, //Default 500; max 1000.
                                   @RequestParam(required = false) Long timestamp) {  //Don't completely understand what is timestamp for
        return service.getAllOrders(sessionId,
                symbol,
                fromId,
                startTime,
                endTime,
                limit);
    }
}

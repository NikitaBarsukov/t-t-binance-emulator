package org.dev.barsukov.controller.fapi.v1;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.EventService;
import org.dev.barsukov.service.crud.CrudOrderService;
import org.dev.barsukov.service.dto.OrderDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/fapi/v1/AllOrders")
public class AllOrdersController {
    private final CrudOrderService crudOrderService;

    @ApiOperation(value = "Gets an order by orderId or origClientOrderId.")
    @GetMapping()
    public List<OrderDto> getOrder(@RequestHeader("X-MBX-APIKEY") String sessionId,
                                   @RequestParam(required = false) String symbol,
                                   @RequestParam(required = false) Long orderId, //If orderId is set, it will get orders >= that orderId. Otherwise most recent orders are returned.
                                   @RequestParam(required = false) Long recvWindow, //Binance doc doesn't contain any info about this prop
                                   @RequestParam(required = false) Long startTime,
                                   @RequestParam(required = false) Long endTime,
                                   @RequestParam(required = false) Integer limit, //Default 500; max 1000.
                                   @RequestParam(required = false) Long timestamp) {  //Don't completely understand what is timestamp for
        return crudOrderService.findAllBy(sessionId,
                                          symbol,
                                          orderId,
                                          startTime,
                                          endTime,
                                          limit);
    }
}



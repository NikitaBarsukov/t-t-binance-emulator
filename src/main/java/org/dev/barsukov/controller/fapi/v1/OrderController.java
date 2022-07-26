package org.dev.barsukov.controller.fapi.v1;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.EventService;
import org.dev.barsukov.service.crud.CrudOrderService;
import org.dev.barsukov.service.dto.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/fapi/v1/order")
public class OrderController {
    private final CrudOrderService crudOrderService;
    private final EventService eventService;

    @ApiOperation(value = "Gets an order by orderId or origClientOrderId.")
    @GetMapping()
    public OrderDto getOrder(@RequestHeader("X-MBX-APIKEY") String sessionId,
                             @RequestParam(required = false) String symbol,
                             @RequestParam(required = false) Long orderId,
                             @RequestParam(required = false) String clientOrderId,
                             @RequestParam(required = false) Long recvWindow,   //Binance doc doesn't contain any info about this prop
                             @RequestParam(required = false) Long timestamp) {  //Dont completely understand what is timestamp for
        return crudOrderService.findBy(sessionId,
                                       symbol,
                                       orderId,
                                       clientOrderId);
    }


    @ApiOperation(value = "Creates an order. If the WS is open puts it in there by listenkey.")
    @PostMapping()
    public ResponseEntity<Object> createOrder(@RequestHeader("X-MBX-APIKEY") String apiKey,
                                              @RequestBody OrderDto dto) {
//        List<JsonNode> sentEvents = eventService.putEvents(dto, apiKey);
        dto.setSessionId(apiKey);
        return ResponseEntity.ok(crudOrderService.save(dto));
    }
}

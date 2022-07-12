package org.dev.barsukov.controller.admin;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.config.websocket.SessionCache;
import org.dev.barsukov.service.crud.CrudTradeService;
import org.dev.barsukov.service.dto.TradeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    private SessionCache sessionCache;
    private CrudTradeService tradeCrud;

    @ApiOperation(value = "Creates an order. If the WS is open puts it in there by listenkey.")
    @PostMapping("/order")
    public ResponseEntity<Object> createOrder(@RequestBody TradeDto dto) {
        tradeCrud.save(dto);
        return ResponseEntity.ok(null);
    }
}

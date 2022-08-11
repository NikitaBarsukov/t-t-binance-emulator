package org.dev.barsukov.controller.fapi.v1;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.api.client.BinanceClient;
import org.dev.barsukov.service.CommonHolderService;
import org.dev.barsukov.service.TradeService;
import org.dev.barsukov.service.dto.TradeDto;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@CacheConfig(cacheNames = "exchangeInfos")
@RequestMapping("/fapi/v1/exchangeInfo")
public class ExchangeInfoController {
    private final CommonHolderService service;

    @ApiOperation(value = "Gets trades by sessionId and symbol.")
    @GetMapping()
    @Cacheable
    public String getExchangeInfo() {
        return service.getExchangeInfo();
    }
}

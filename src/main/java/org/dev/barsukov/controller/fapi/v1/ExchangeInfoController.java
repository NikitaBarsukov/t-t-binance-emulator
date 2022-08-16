package org.dev.barsukov.controller.fapi.v1;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.CommonHolderService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/fapi/v1/exchangeInfo")
public class ExchangeInfoController {
    private final CommonHolderService service;

    @ApiOperation(value = "Cacheable proxy answer from binance or from Db if it available")
    @GetMapping()
    @Cacheable("exchangeInfos")
    public String getExchangeInfo() {
        return service.getExchangeInfo();
    }
}

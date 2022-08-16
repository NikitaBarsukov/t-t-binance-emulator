package org.dev.barsukov.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="binance-api-service", url="${binance-api.ribbon.listOfServers}")
public interface BinanceClient {

    @GetMapping("/exchangeInfo")
    String getExchangeInfo();
}

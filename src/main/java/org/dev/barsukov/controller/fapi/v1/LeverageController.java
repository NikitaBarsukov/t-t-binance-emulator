package org.dev.barsukov.controller.fapi.v1;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.CommonHolderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/fapi/v1/leverage")
public class LeverageController {
    private final CommonHolderService service;

    @ApiOperation(value = "User leverage by api-key")
    @GetMapping()
    public String getLeverage(@RequestHeader("X-MBX-APIKEY") String apiKey) {
        return service.getLeverage(apiKey);
    }
}

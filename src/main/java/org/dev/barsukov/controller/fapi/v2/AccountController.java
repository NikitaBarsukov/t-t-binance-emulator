package org.dev.barsukov.controller.fapi.v2;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.dev.barsukov.service.CommonHolderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("fapi/v2/account")
public class AccountController {
    private final CommonHolderService service;

    @ApiOperation(value = "User Account details by api-key")
    @GetMapping()
    public Object getAccount (@RequestHeader("X-MBX-APIKEY") String apiKey, String symbol) {
        return service.getAccount(apiKey);
    }
}

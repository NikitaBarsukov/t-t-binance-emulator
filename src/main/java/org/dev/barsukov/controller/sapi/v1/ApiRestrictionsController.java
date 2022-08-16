package org.dev.barsukov.controller.sapi.v1;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.dev.barsukov.service.CommonHolderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/sapi/v1/account/apiRestrictions")
public class ApiRestrictionsController {
    private final CommonHolderService service;

    @ApiOperation(value = "User Restrictions by api-key")
    @GetMapping()
    public Object getRestrictions (@RequestHeader("X-MBX-APIKEY") String apiKey) {
        return service.getRestrictions(apiKey);
    }
}

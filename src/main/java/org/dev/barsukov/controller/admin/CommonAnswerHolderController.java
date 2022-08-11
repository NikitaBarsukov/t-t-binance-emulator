package org.dev.barsukov.controller.admin;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.CommonHolderService;
import org.dev.barsukov.service.dto.CommonHolderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/admin/mock")
public class CommonAnswerHolderController {
    private final CommonHolderService service;

    @ApiOperation(value = "Set endpoints answer")
    @PostMapping()
    public ResponseEntity<Object> createMockAnswer(@RequestHeader("X-MBX-APIKEY") String apiKey,
                                                   @RequestBody CommonHolderDto dto) {
        return ResponseEntity.ok(service.addAnswer(dto, apiKey));
    }
}

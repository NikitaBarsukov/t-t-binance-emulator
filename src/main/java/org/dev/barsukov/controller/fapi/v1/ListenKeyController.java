package org.dev.barsukov.controller.fapi.v1;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.ListenKeyService;
import org.dev.barsukov.service.dto.ListenKeyDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/fapi/v1/listenKey")
public class ListenKeyController {
    final ListenKeyService service;

    @ApiOperation(value = "Allows to get listenKey by inner id by X-MBX-APIKEY header")
    @GetMapping(value = "/key/{keyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ListenKeyDto getListenKey(@PathVariable Long keyId) {
        return service.findOne(keyId);
    }

    @ApiOperation(value = "Allows to emulate open session by generating listenKey bound with X-MBX-APIKEY header")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListenKeyDto> updateUser(@RequestHeader("X-MBX-APIKEY") String apiKey) {
        log.info("User " + apiKey +" call generateUser()");
        return new ResponseEntity<>(service.generate(apiKey), HttpStatus.OK);
    }

    @ApiOperation(value = "Allows to update listenKey valid time by X-MBX-APIKEY header")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListenKeyDto> updateListenKey(@RequestHeader("X-MBX-APIKEY") String apiKey) {
        log.info("Call updateListenKey() with apiKey: {}", apiKey);
        service.update(apiKey);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Allows to invalidate listenKey session by X-MBX-APIKEY header")
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> deleteListenKey(@RequestHeader("X-MBX-APIKEY") String apiKey) {
        log.info("Call deleteUser() with apiKey: {}", apiKey);
        service.invalidate(apiKey);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
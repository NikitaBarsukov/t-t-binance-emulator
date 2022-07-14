package org.dev.barsukov.controller.fapi.v1;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.ListenKeyService;
import org.dev.barsukov.service.crud.CrudListenKeyService;
import org.dev.barsukov.service.dto.ListenKeyDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/fapi/v1/listenKey")
public class ListenKeyController {
    final CrudListenKeyService crudService;
    final ListenKeyService service;

    @ApiOperation(value = "Allows to get listenKey by id")
    @GetMapping(value = "/key/{keyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ListenKeyDto getListenKey(@PathVariable Long keyId) {
        return crudService.findOne(keyId);
    }

    @ApiOperation(value = "Allows to update listenKey data")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListenKeyDto> updateUser(@RequestHeader("X-MBX-APIKEY") String apiKey) {
        log.info("User " + apiKey +" call generateUser()");
        return new ResponseEntity<>(service.generate(apiKey), HttpStatus.OK);
    }

    @ApiOperation(value = "Allows to create listenKey")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListenKeyDto> createListenKey(@RequestBody ListenKeyDto dto) {
        log.info("Call updateListenKey() with params: {}", dto);
        return new ResponseEntity<>(crudService.save(dto), HttpStatus.OK);
    }

    @ApiOperation(value = "Allows to delete listenKey by id")
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> deleteUser(@PathVariable Long keyId) {
        log.info("Call deleteUser() with params: {}", keyId);
        crudService.delete(keyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
package org.dev.barsukov.controller.admin;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.crud.CrudKeyService;
import org.dev.barsukov.service.dto.KeyDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class KeyController {
    final CrudKeyService service;

    @ApiOperation(value = "Allows to get listenKey by id")
    @GetMapping(value = "/key/{keyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public KeyDto getUser(@PathVariable Long keyId) {
        return service.findOne(keyId);
    }

    @ApiOperation(value = "Allows to create listenKey")
    @PostMapping(value = "/key", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KeyDto> createUser(@RequestBody KeyDto keys) {
        log.info("Call createUser() with params: {}", keys);
        return new ResponseEntity<>(service.save(keys), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Allows to update listenKey data")
    @PutMapping(value = "/key", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KeyDto> updateUser(@RequestBody KeyDto keyDto) {
        log.info("Call createUser() with params: {}", keyDto);
        return new ResponseEntity<>(service.save(keyDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Allows to delete listenKey by id")
    @DeleteMapping(value = "/key/{keyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> deleteUser(@PathVariable Long keyId) {
        log.info("Call deleteUser() with params: {}", keyId);
        service.delete(keyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
package org.dev.barsukov.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.crud.CrudEventHolderService;
import org.dev.barsukov.service.dto.EventHolderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/admin/event")
public class EventController {
    private CrudEventHolderService eventHolderCrud;

    @ApiOperation(value = "Helps handle events for sending in WS")
    @PostMapping()
    public ResponseEntity<Object> createEvent(@RequestBody EventHolderDto dto) throws JsonProcessingException {
        eventHolderCrud.save(dto);
        return ResponseEntity.ok(null);
    }
}
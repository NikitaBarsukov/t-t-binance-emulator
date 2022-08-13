package org.dev.barsukov.controller.admin;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.CommonHolderService;
import org.dev.barsukov.service.dto.CommonHolderDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
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

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/admin/mock")
public class CommonAnswerHolderController {
    private final CommonHolderService service;

    @ApiOperation(value = "Get CommonAnswerHolder by apikey and endpoint")
    @GetMapping()
    public List<CommonHolderDto> getCommonAnswerHolders(@RequestHeader("X-MBX-APIKEY") String apiKey,
                                                        String endpoint) {
        return service.getAllBy(apiKey, endpoint);
    }

    @ApiOperation(value = "Gets all CommonAnswerHolder by id")
    @GetMapping("/{id}")
    public ResponseEntity<CommonHolderDto> getCommonAnswerHolder(@PathVariable("id") Long id) {
        return ResponseEntity.of(service.get(id));
    }

    @ApiOperation(value = "Add endpoints answer by apikey and endpoint")
    @PostMapping()
    @CacheEvict(value = "exchangeInfos", allEntries = true)
    public ResponseEntity<Object> createMockAnswer(@RequestHeader("X-MBX-APIKEY") String apiKey,
                                                   @RequestBody CommonHolderDto dto) {
        return ResponseEntity.ok(service.addAnswer(dto, apiKey));
    }

    @ApiOperation(value = "Update CommonAnswerHolder by id")
    @PutMapping("/{id}")
    public CommonHolderDto updateCommonForOrder(@RequestHeader("X-MBX-APIKEY") String apiKey,
                                                @PathVariable("id") Long id,
                                                @RequestBody CommonHolderDto dto) {
        dto.setApiKey(apiKey);
        dto.setId(id);
        return service.updateAnswer(dto);
    }

    @ApiOperation(value = "Delete CommonAnswerHolder on endpoint")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCommonForOrder(@PathVariable("id") Long id) {
        service.deleteAnswer(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}

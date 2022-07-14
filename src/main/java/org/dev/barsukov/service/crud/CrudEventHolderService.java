package org.dev.barsukov.service.crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.dev.barsukov.service.dto.EventHolderDto;

import java.util.List;

public interface CrudEventHolderService {

    EventHolderDto findOne(Long id) throws JsonProcessingException;

    void delete(Long id);

    EventHolderDto save(EventHolderDto dto) throws JsonProcessingException;

    List<JsonNode> getActiveEventsBy(String apiKey);
}
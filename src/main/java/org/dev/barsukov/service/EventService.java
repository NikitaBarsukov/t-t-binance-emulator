package org.dev.barsukov.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.dev.barsukov.service.dto.OrderDto;

import java.util.List;

public interface EventService {

    List<JsonNode> putEvents(OrderDto dto, String apiKey);

}

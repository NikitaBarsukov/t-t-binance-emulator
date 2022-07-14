package org.dev.barsukov.service;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface EventService {

    List<JsonNode> putEvents(String apiKey);

}

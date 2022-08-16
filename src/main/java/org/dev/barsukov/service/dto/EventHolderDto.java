package org.dev.barsukov.service.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventHolderDto {
    Long id;
    JsonNode event;
    String sessionId;
    String apiKey;
    String secretKey;
    Integer sendOrder;
}
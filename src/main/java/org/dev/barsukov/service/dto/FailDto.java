package org.dev.barsukov.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FailDto {
    Long id;
    String symbol;
    String endpoint;
    JsonNode payload;
    String apiKey;
    Boolean isActive;

    public JsonNode getPayload() {
        return payload;
    }
}
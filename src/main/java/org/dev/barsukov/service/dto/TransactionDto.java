package org.dev.barsukov.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {
    Long id;
    String symbol;
    String incomeType;
    String income;
    String asset;
    String info;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    Timestamp time;
    String tranId;
    String tradeId;
}
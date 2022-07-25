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
public class OrderDto {
    Long id;
    String sessionId;
    String avgPrice;
    String clientOrderId;
    String cumQuote;
    String executedQty;
    Long orderId;
    String origQty;
    String origType;
    String price;
    Boolean reduceOnly;
    String side;
    String positionSide;
    String status;
    String stopPrice;
    Boolean closePosition;
    String symbol;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    Timestamp time;
    String timeInForce;
    String type;
    String activatePrice;
    String priceRate;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    Timestamp updateTime;
    String workingType;
    Boolean priceProtect;
}
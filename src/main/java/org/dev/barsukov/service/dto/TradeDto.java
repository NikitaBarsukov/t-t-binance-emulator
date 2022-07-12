package org.dev.barsukov.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeDto {
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
    Timestamp time;
    String timeInForce;
    String type;
    String activatePrice;
    String priceRate;
    Timestamp updateTime;
    String workingType;
    Boolean priceProtect;
}
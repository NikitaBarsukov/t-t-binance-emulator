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
    Boolean buyer;
    String commission;
    String commissionAsset;
    Boolean maker;
    Long orderId;
    String price;
    String qty;
    String quoteQty;
    String realizedPnl;
    String side;
    String positionSide;
    String symbol;
    Timestamp time;
}
package org.dev.barsukov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Builder(toBuilder = true)
@Data
@Entity
@Table(name="trade_order")
@NoArgsConstructor
@AllArgsConstructor
public class TradeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
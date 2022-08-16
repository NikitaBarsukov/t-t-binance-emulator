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
@Table(name = "trade")
@NoArgsConstructor
@AllArgsConstructor
public class TradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String apiKey;
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
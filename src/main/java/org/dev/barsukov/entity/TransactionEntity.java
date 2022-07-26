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
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String symbol;
    String incomeType;
    String income;
    String asset;
    String info;
    Timestamp time;
    String tranId;
    String tradeId;
    String apiKey;
}
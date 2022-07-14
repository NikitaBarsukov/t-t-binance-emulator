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
@Table(name="listenkey")
@NoArgsConstructor
@AllArgsConstructor
public class ListenKeyEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    String apiKey;
    String ApiSecret;
    String listenKey;
    Timestamp registerTime;
    Timestamp validTime;
}
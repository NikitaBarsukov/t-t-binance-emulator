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

@Builder(toBuilder = true)
@Data
@Entity
@Table(name="key")
@NoArgsConstructor
@AllArgsConstructor
public class KeyEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    String key;
    String secret;
}
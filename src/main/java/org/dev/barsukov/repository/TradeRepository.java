package org.dev.barsukov.repository;

import org.dev.barsukov.entity.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<TradeEntity, Long> {
}

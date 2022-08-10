package org.dev.barsukov.repository;

import org.dev.barsukov.entity.FailOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FailOrderRepository extends JpaRepository<FailOrderEntity, Long> {

		List<FailOrderEntity> findAllByApiKeyAndSymbolAndIsActiveTrue(String apikey, String symbol);
}

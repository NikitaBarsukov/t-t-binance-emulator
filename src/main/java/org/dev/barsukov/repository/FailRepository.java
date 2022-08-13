package org.dev.barsukov.repository;

import org.dev.barsukov.entity.FailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FailRepository extends JpaRepository<FailEntity, Long> {

	List<FailEntity> findAllByApiKeyAndSymbolAndEndpointAndIsActiveTrue(String apikey, String symbol, String endpoint);

	List<FailEntity> findAllByApiKeyAndEndpointAndIsActiveTrue(String apikey, String endpoint);

	List<FailEntity> findAllByEndpoint(String endpoint);
}

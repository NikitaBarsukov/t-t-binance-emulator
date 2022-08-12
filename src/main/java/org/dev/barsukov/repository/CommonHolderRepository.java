package org.dev.barsukov.repository;

import org.dev.barsukov.entity.CommonHolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonHolderRepository extends JpaRepository<CommonHolderEntity, Long> {
	CommonHolderEntity findFirstByEndpointAndIsActiveTrue(String endpoint);

	List<CommonHolderEntity> findAllByEndpointAndApiKey(String endpoint, String apiKey);

	CommonHolderEntity findFirstByEndpointAndApiKey(String endpoint, String apiKey);

}

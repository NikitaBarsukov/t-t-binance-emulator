package org.dev.barsukov.repository;

import org.dev.barsukov.entity.CommonHolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonHolderRepository extends JpaRepository<CommonHolderEntity, Long> {
		CommonHolderEntity findAllByEndpointAndIsActiveTrue(String endpoint);
}

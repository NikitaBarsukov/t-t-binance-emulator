package org.dev.barsukov.repository;

import org.dev.barsukov.entity.ListenKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface ListenKeyRepository extends JpaRepository<ListenKeyEntity, Long> {

    ListenKeyEntity findTopByApiKeyAndValidTimeIsGreaterThan(String apiKey, Timestamp validTime);
}
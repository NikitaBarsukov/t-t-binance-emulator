package org.dev.barsukov.repository;

import org.dev.barsukov.entity.ListenKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListenKeyRepository extends JpaRepository<ListenKeyEntity, Long> {

    ListenKeyEntity findByApiKey(String apiKey);
}
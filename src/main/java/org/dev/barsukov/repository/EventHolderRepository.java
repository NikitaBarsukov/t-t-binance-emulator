package org.dev.barsukov.repository;

import org.dev.barsukov.entity.EventHolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventHolderRepository extends JpaRepository<EventHolderEntity, Long> {

        List<EventHolderEntity> findByApiKeyAndSendOrderIsNotNull(String ApiKey);
}

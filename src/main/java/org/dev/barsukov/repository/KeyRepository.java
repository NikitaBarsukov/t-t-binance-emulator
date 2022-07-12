package org.dev.barsukov.repository;

import org.dev.barsukov.entity.KeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyRepository extends JpaRepository<KeyEntity, Long> {
}
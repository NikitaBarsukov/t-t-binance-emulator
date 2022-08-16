package org.dev.barsukov.repository;

import org.dev.barsukov.entity.TransactionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query("SELECT t FROM TransactionEntity t " +
            "WHERE (:apiKey is null or t.apiKey = :apiKey) and " +
            "(:symbol is null or t.symbol = :symbol) and " +
            "(:incomeType is null or t.incomeType = :incomeType) and " +
            "t.time BETWEEN :startTime and :endTime ")
    List<TransactionEntity> findAllByUrlParams(@Param("apiKey") String apiKey,
                                         @Param("symbol") String symbol,
                                         @Param("incomeType") String incomeType,
                                         @Param("startTime") Timestamp startTime,
                                         @Param("endTime") Timestamp endTime,
                                         Pageable pageable);
}



package org.dev.barsukov.repository;

import org.dev.barsukov.entity.OrderEntity;
import org.dev.barsukov.entity.TradeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface TradeRepository extends JpaRepository<TradeEntity, Long> {

    @Query("SELECT t FROM TradeEntity t " +
            "WHERE (:apiKey is null or t.apiKey = :apiKey) and " +
            "(:symbol is null or t.symbol = :symbol) and " +
            "(:tradeId is null or t.id <= :tradeId) and " +
            "t.time BETWEEN :startTime and :endTime ")
    List<TradeEntity> findAllByUrlParams(@Param("apiKey") String apiKey,
                                         @Param("symbol") String symbol,
                                         @Param("tradeId") Long tradeId,
                                         @Param("startTime") Timestamp startTime,
                                         @Param("endTime") Timestamp endTime,
                                         Pageable pageable);
}

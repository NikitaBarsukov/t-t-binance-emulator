package org.dev.barsukov.repository;

import org.dev.barsukov.entity.OrderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT o FROM OrderEntity o " +
            "WHERE (:sessionId is null or o.sessionId = :sessionId) and" +
            "(:symbol is null or o.symbol = :symbol) and" +
            "(:orderId is null or o.orderId = :orderId) and" +
            "(:origClientOrderId is null or o.clientOrderId = :origClientOrderId)")
    OrderEntity findOrderByUrlParams(@Param("sessionId") String sessionId,
                                     @Param("symbol") String symbol,
                                     @Param("orderId") Long orderId,
                                     @Param("origClientOrderId") String origClientOrderId);


    @Query("SELECT o FROM OrderEntity o " +
            "WHERE (:sessionId is null or o.sessionId = :sessionId) and " +
            "(:symbol is null or o.symbol = :symbol) and " +
            "(:orderId is null or o.orderId <= :orderId) and " +
            "o.time BETWEEN :startTime and :endTime ")
    List<OrderEntity> findAllByUrlParams(@Param("sessionId") String sessionId,
                                         @Param("symbol") String symbol,
                                         @Param("orderId") Long orderId,
                                         @Param("startTime") Timestamp startTime,
                                         @Param("endTime") Timestamp endTime,
                                         Pageable pageable);
}
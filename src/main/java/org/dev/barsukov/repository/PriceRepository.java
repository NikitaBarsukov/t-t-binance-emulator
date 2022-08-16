package org.dev.barsukov.repository;

import org.dev.barsukov.entity.PriceEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p " +
            "WHERE p.apiKey = :apiKey and " +
            "(:symbol is null or p.symbol = :symbol)")
    List<PriceEntity> findAllBySymbol(@Param("apiKey") String apiKey,
                                      @Param("symbol") String symbol);
}

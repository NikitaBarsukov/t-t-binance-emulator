package org.dev.barsukov.service.crud;

import org.dev.barsukov.entity.FailOrderEntity;
import org.dev.barsukov.service.dto.FailOrderDto;


import java.util.List;
import java.util.Optional;

public interface CrudFailOrderService {

    Optional<FailOrderEntity> findOne(Long id);

    List<FailOrderEntity> findAllByApiKeyAndSymbol(String symbol,String apikey);

    FailOrderDto save(FailOrderDto dto, String apiKey);

    FailOrderDto save(FailOrderEntity entity);

    List<FailOrderDto> save(List<FailOrderDto> dto);

    List<FailOrderEntity> findAll();

    FailOrderEntity update(FailOrderEntity entity);

    void delete(Long id);
}
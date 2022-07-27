package org.dev.barsukov.service.crud;

import org.dev.barsukov.entity.FailOrderEntity;
import org.dev.barsukov.service.dto.FailOrderDto;


import java.util.List;

public interface CrudFailOrderService {

    FailOrderDto findOne(Long id);

    void delete(Long id);

    FailOrderDto save(FailOrderDto dto, String apiKey);

    FailOrderDto save(FailOrderEntity entity);

    List<FailOrderDto> save(List<FailOrderDto> dto, String apiKey);
}
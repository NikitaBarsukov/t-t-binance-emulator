package org.dev.barsukov.service.crud;

import org.dev.barsukov.entity.FailEntity;
import org.dev.barsukov.service.dto.FailDto;


import java.util.List;
import java.util.Optional;

public interface CrudFailService {

    Optional<FailEntity> findOne(Long id);

    List<FailEntity> findAllByApiKeyAndSymbolAndEndpoint(String symbol, String apikey, String endpoint);

    FailDto save(FailDto dto, String apiKey);

    FailDto save(FailEntity entity);

    List<FailDto> save(List<FailDto> dto);

    List<FailEntity> findAll(String endpoint);

    FailEntity update(FailEntity entity);

    void delete(Long id);

    List<FailEntity> findAllByApiKeyAndSymbolAndEndpoint(String sessionId, String endpoint);
}
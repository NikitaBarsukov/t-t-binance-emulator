package org.dev.barsukov.service.crud;


import org.dev.barsukov.entity.ListenKeyEntity;
import org.dev.barsukov.service.dto.ListenKeyDto;

public interface CrudListenKeyService {

    ListenKeyDto findOne(Long id);

    void delete(Long id);

    ListenKeyDto save(ListenKeyDto dto);

    ListenKeyEntity save(ListenKeyEntity entity);

    ListenKeyEntity findActualByApiKey(String apiKey);
}
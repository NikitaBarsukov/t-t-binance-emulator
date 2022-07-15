package org.dev.barsukov.service.crud;


import org.dev.barsukov.entity.KeyEntity;
import org.dev.barsukov.service.dto.KeyDto;

import java.util.List;

public interface CrudKeyService {

    List<KeyDto> findAll();

    KeyDto findOne(Long id);

    void delete(Long id);

    KeyDto save(KeyDto dto);
}
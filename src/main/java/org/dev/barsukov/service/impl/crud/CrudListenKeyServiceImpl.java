package org.dev.barsukov.service.impl.crud;

import lombok.AllArgsConstructor;
import org.dev.barsukov.converter.ListenKeyConverter;
import org.dev.barsukov.entity.ListenKeyEntity;
import org.dev.barsukov.exception.NoSuchKeyException;
import org.dev.barsukov.repository.ListenKeyRepository;
import org.dev.barsukov.service.crud.CrudListenKeyService;
import org.dev.barsukov.service.dto.ListenKeyDto;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@AllArgsConstructor
public class CrudListenKeyServiceImpl implements CrudListenKeyService {
    private final ListenKeyRepository repository;
    private final ListenKeyConverter converter;

    @Override
    public ListenKeyDto findOne(Long id) {
        return converter.toDto(repository.findById(id).orElseThrow(NoSuchKeyException::new));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ListenKeyDto save(ListenKeyDto dto) {
        return converter.toDto(repository.save(converter.fromDto(dto)));
    }

    @Override
    public ListenKeyEntity save(ListenKeyEntity entity) {
        return repository.save(entity);
    }

    @Override
    public ListenKeyEntity findByApiKey(String apiKey) {
        return repository.findTopByApiKeyAndValidTimeIsGreaterThan(apiKey, Timestamp.from(Instant.now()));
    }
}

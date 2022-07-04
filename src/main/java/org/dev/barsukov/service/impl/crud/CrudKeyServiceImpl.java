package org.dev.barsukov.service.impl.crud;

import org.dev.barsukov.converter.KeyConverter;
import org.dev.barsukov.entity.KeyEntity;
import org.dev.barsukov.repository.KeyRepository;
import org.dev.barsukov.service.crud.CrudKeyService;
import org.dev.barsukov.service.dto.KeyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.List;

@Service
public class CrudKeyServiceImpl implements CrudKeyService {
    private final KeyRepository repository;
    private final KeyConverter converter;

    @Autowired
    public CrudKeyServiceImpl(KeyRepository repository, KeyConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<KeyDto> findAll() {
        return converter.toDto(repository.findAll());
    }

    @Override
    public KeyDto findOne(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public KeyDto save(KeyDto dto) {
        KeyEntity entity = converter.fromDto(dto);
        KeyEntity result = repository.save(entity);
        return converter.toDto(result);
    }
}

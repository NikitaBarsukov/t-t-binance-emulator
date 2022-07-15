package org.dev.barsukov.service.impl.crud;

import lombok.AllArgsConstructor;
import org.dev.barsukov.converter.KeyConverter;
import org.dev.barsukov.exception.NoSuchKeyException;
import org.dev.barsukov.repository.KeyRepository;
import org.dev.barsukov.service.crud.CrudKeyService;
import org.dev.barsukov.service.dto.KeyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CrudKeyServiceImpl implements CrudKeyService {
    private final KeyRepository repository;
    private final KeyConverter converter;

    @Override
    public List<KeyDto> findAll() {
        return converter.toDto(repository.findAll());
    }

    @Override
    public KeyDto findOne(Long id) {
        return converter.toDto(repository.findById(id).orElseThrow(NoSuchKeyException::new));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public KeyDto save(KeyDto dto) {
        return converter.toDto(repository.save(converter.fromDto(dto)));
    }
}

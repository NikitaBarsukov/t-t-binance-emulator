package org.dev.barsukov.service.impl.crud;

import lombok.AllArgsConstructor;
import org.dev.barsukov.converter.CrudFailConverter;
import org.dev.barsukov.entity.CrudFailEntity;
import org.dev.barsukov.exception.NoSuchCrudFailException;
import org.dev.barsukov.repository.CrudFailRepository;
import org.dev.barsukov.service.crud.CrudCrudFailService;
import org.dev.barsukov.service.dto.CrudFailDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CrudCrudFailServiceImpl implements CrudCrudFailService {
    private final CrudFailRepository repository;
    private final CrudFailConverter converter;

    @Override
    public CrudFailDto findOne(Long id) {
        return converter.toDto(repository.findById(id).orElseThrow(NoSuchCrudFailException::new));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


	@Override
	public CrudFailDto save(CrudFailEntity entity) {
        return converter.toDto(repository.save(entity));
	}

    @Override
    public List<CrudFailDto> save(List<CrudFailDto> dto, String apiKey) {
        List<CrudFailEntity> entities = dto.stream()
                .map(x -> converter.fromDto(x, apiKey))
                .toList();
        repository.saveAll(entities);
        return dto;
    }

    @Override
    public CrudFailDto save(CrudFailDto dto, String apiKey) {
        return converter.toDto(repository.save(converter.fromDto(dto, apiKey)));
    }
}

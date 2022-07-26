package org.dev.barsukov.service.impl.crud;

import lombok.AllArgsConstructor;
import org.dev.barsukov.converter.LeverageConverter;
import org.dev.barsukov.entity.LeverageEntity;
import org.dev.barsukov.exception.NoSuchLeverageException;
import org.dev.barsukov.repository.LeverageRepository;
import org.dev.barsukov.service.crud.CrudLeverageService;
import org.dev.barsukov.service.dto.LeverageDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CrudLeverageServiceImpl implements CrudLeverageService {
    private final LeverageRepository repository;
    private final LeverageConverter converter;

    @Override
    public LeverageDto findOne(Long id) {
        return converter.toDto(repository.findById(id).orElseThrow(NoSuchLeverageException::new));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


	@Override
	public LeverageDto save(LeverageEntity entity) {
        return converter.toDto(repository.save(entity));
	}

    @Override
    public List<LeverageDto> save(List<LeverageDto> dto, String apiKey) {
        List<LeverageEntity> entities = dto.stream()
                .map(x -> converter.fromDto(x, apiKey))
                .toList();
        repository.saveAll(entities);
        return dto;
    }

    @Override
    public LeverageDto save(LeverageDto dto, String apiKey) {
        return converter.toDto(repository.save(converter.fromDto(dto, apiKey)));
    }
}

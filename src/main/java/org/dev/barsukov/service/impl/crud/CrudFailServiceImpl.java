package org.dev.barsukov.service.impl.crud;

import lombok.AllArgsConstructor;
import org.dev.barsukov.converter.FailConverter;
import org.dev.barsukov.entity.FailEntity;
import org.dev.barsukov.repository.FailRepository;
import org.dev.barsukov.service.crud.CrudFailService;
import org.dev.barsukov.service.dto.FailDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CrudFailServiceImpl implements CrudFailService {
    private final FailRepository repository;
    private final FailConverter converter;

    @Override
    public Optional<FailEntity> findOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


	@Override
	public FailDto save(FailEntity entity) {
        return converter.toDto(repository.save(entity));
	}

    @Override
    public List<FailDto> save(List<FailDto> dto) {
        List<FailEntity> entities = dto.stream()
                .map(converter::fromDto)
                .toList();
        repository.saveAll(entities);
        return dto;
    }

	@Override
	public List<FailEntity> findAll() {
		return repository.findAll();
	}

    @Override
    public FailEntity update(FailEntity entity) {
        return repository.save(entity);
    }

    @Override
	public List<FailEntity> findAllByApiKeyAndSymbolAndEndpoint(String symbol, String apikey, String endpoint) {
		return repository.findAllByApiKeyAndSymbolAndEndpointAndIsActiveTrue(apikey, symbol, endpoint);
	}

	@Override
    public FailDto save(FailDto dto, String apiKey) {
        return converter.toDto(repository.save(converter.fromDto(dto)));
    }
}

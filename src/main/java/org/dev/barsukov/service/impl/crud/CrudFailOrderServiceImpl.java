package org.dev.barsukov.service.impl.crud;

import lombok.AllArgsConstructor;
import org.dev.barsukov.converter.FailOrderConverter;
import org.dev.barsukov.entity.FailOrderEntity;
import org.dev.barsukov.repository.FailOrderRepository;
import org.dev.barsukov.service.crud.CrudFailOrderService;
import org.dev.barsukov.service.dto.FailOrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CrudFailOrderServiceImpl implements CrudFailOrderService {
    private final FailOrderRepository repository;
    private final FailOrderConverter converter;

    @Override
    public Optional<FailOrderEntity> findOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


	@Override
	public FailOrderDto save(FailOrderEntity entity) {
        return converter.toDto(repository.save(entity));
	}

    @Override
    public List<FailOrderDto> save(List<FailOrderDto> dto) {
        List<FailOrderEntity> entities = dto.stream()
                .map(converter::fromDto)
                .toList();
        repository.saveAll(entities);
        return dto;
    }

	@Override
	public List<FailOrderEntity> findAll() {
		return repository.findAll();
	}

    @Override
    public FailOrderEntity update(FailOrderEntity entity) {
        return repository.save(entity);
    }

    @Override
	public List<FailOrderEntity> findAllByApiKeyAndSymbol(String symbol, String apikey) {
		return repository.findAllByApiKeyAndSymbolAndIsActiveTrue(apikey, symbol);
	}

	@Override
    public FailOrderDto save(FailOrderDto dto, String apiKey) {
        return converter.toDto(repository.save(converter.fromDto(dto)));
    }
}

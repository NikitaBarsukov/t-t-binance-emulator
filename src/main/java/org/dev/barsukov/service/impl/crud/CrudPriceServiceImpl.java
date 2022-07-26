package org.dev.barsukov.service.impl.crud;

import lombok.AllArgsConstructor;
import org.dev.barsukov.converter.PriceConverter;
import org.dev.barsukov.entity.PriceEntity;
import org.dev.barsukov.exception.NoSuchPriceException;
import org.dev.barsukov.repository.PriceRepository;
import org.dev.barsukov.service.crud.CrudPriceService;
import org.dev.barsukov.service.dto.PriceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CrudPriceServiceImpl implements CrudPriceService {
    private final PriceRepository repository;
    private final PriceConverter converter;

    @Override
    public PriceDto findOne(Long id) {
        return converter.toDto(repository.findById(id).orElseThrow(NoSuchPriceException::new));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


	@Override
	public PriceDto save(PriceEntity entity) {
        return converter.toDto(repository.save(entity));
	}

    @Override
    public List<PriceEntity> save(List<PriceEntity> entities) {
        repository.saveAll(entities);
        return entities;
    }

    @Override
    public List<PriceEntity> getAllBySymbol(String apiKey, String symbol) {
        return repository.findAllBySymbol(apiKey, symbol);
    }

    @Override
    public PriceDto save(PriceDto dto, String apiKey) {
        return converter.toDto(repository.save(converter.fromDto(dto, apiKey)));
    }
}

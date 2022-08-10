package org.dev.barsukov.service.impl.crud;

import lombok.AllArgsConstructor;
import org.dev.barsukov.converter.ExchangeInfoHolderConverter;
import org.dev.barsukov.entity.ExchangeInfoHolderEntity;
import org.dev.barsukov.exception.NoSuchExchangeInfoHolderException;
import org.dev.barsukov.repository.ExchangeInfoHolderRepository;
import org.dev.barsukov.service.crud.CrudExchangeInfoHolderService;
import org.dev.barsukov.service.dto.ExchangeInfoHolderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CrudExchangeInfoHolderServiceImpl implements CrudExchangeInfoHolderService {
    private final ExchangeInfoHolderRepository repository;
    private final ExchangeInfoHolderConverter converter;

    @Override
    public ExchangeInfoHolderDto findOne(Long id) {
        return converter.toDto(repository.findById(id).orElseThrow(NoSuchExchangeInfoHolderException::new));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


	@Override
	public ExchangeInfoHolderDto save(ExchangeInfoHolderEntity entity) {
        return converter.toDto(repository.save(entity));
	}

    @Override
    public List<ExchangeInfoHolderDto> save(List<ExchangeInfoHolderDto> dto, String apiKey) {
        List<ExchangeInfoHolderEntity> entities = dto.stream()
                .map(x -> converter.fromDto(x, apiKey))
                .toList();
        repository.saveAll(entities);
        return dto;
    }

    @Override
    public ExchangeInfoHolderDto save(ExchangeInfoHolderDto dto, String apiKey) {
        return converter.toDto(repository.save(converter.fromDto(dto, apiKey)));
    }
}

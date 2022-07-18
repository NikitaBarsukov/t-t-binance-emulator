package org.dev.barsukov.service.impl.crud;

import lombok.AllArgsConstructor;
import org.dev.barsukov.converter.TradeConverter;
import org.dev.barsukov.exception.NoSuchTradeException;
import org.dev.barsukov.repository.TradeRepository;
import org.dev.barsukov.service.crud.CrudTradeService;
import org.dev.barsukov.service.dto.TradeDto;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CrudTradeServiceImpl implements CrudTradeService {
    private final TradeRepository repository;
    private final TradeConverter converter;

    @Override
    public TradeDto findOne(Long id) {
        return converter.toDto(repository.findById(id).orElseThrow(NoSuchTradeException::new));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public TradeDto save(TradeDto dto, String apiKey) {
        return converter.toDto(repository.save(converter.fromDto(dto, apiKey)));
    }
}

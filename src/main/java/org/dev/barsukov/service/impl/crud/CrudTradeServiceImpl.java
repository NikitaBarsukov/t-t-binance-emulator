package org.dev.barsukov.service.impl.crud;

import lombok.AllArgsConstructor;
import org.dev.barsukov.converter.TradeConverter;
import org.dev.barsukov.entity.TradeEntity;
import org.dev.barsukov.exception.NoSuchTradeException;
import org.dev.barsukov.repository.TradeRepository;
import org.dev.barsukov.service.crud.CrudTradeService;
import org.dev.barsukov.service.dto.TradeDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

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
	public TradeDto save(TradeEntity entity) {
        return converter.toDto(repository.save(entity));
	}

    @Override
    public List<TradeEntity> findAllBy(String sessionId, String symbol, Long fromId, Timestamp startTime, Timestamp endTime, Integer limit) {
        return repository.findAllByUrlParams(sessionId,
                symbol,
                fromId,
                startTime,
                endTime,
                PageRequest.of(0, limit));


    }

    @Override
    public TradeDto save(TradeDto dto, String apiKey) {
        return converter.toDto(repository.save(converter.fromDto(dto, apiKey)));
    }
}

package org.dev.barsukov.service.impl.crud;

import lombok.AllArgsConstructor;
import org.dev.barsukov.converter.TransactionConverter;
import org.dev.barsukov.entity.TransactionEntity;
import org.dev.barsukov.exception.NoSuchTransactionException;
import org.dev.barsukov.repository.TransactionRepository;
import org.dev.barsukov.service.crud.CrudTransactionService;
import org.dev.barsukov.service.dto.TransactionDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class CrudTransactionServiceImpl implements CrudTransactionService {
    private final TransactionRepository repository;
    private final TransactionConverter converter;

    @Override
    public TransactionDto findOne(Long id) {
        return converter.toDto(repository.findById(id).orElseThrow(NoSuchTransactionException::new));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

	@Override
	public void delete(String apiKey) {
		repository.deleteAll();
	}


	@Override
	public TransactionDto save(TransactionEntity entity) {
        return converter.toDto(repository.save(entity));
	}

    @Override
    public List<TransactionDto> save(List<TransactionDto> dto, String apiKey) {
        List<TransactionEntity> entities = dto.stream()
                .map(x -> converter.fromDto(x, apiKey))
                .toList();
        repository.saveAll(entities);
        return dto;
    }

    @Override
    public List<TransactionEntity> findAllBy(String apiKey,
                                             String symbol,
                                             String incomeType,
                                             Timestamp startTime,
                                             Timestamp endTime,
                                             Integer limit) {
        return repository.findAllByUrlParams(apiKey,
                symbol,
                incomeType,
                startTime,
                endTime,
                PageRequest.of(0, limit));
    }

    @Override
    public TransactionDto save(TransactionDto dto, String apiKey) {
        return converter.toDto(repository.save(converter.fromDto(dto, apiKey)));
    }
}

package org.dev.barsukov.service.crud;

import org.dev.barsukov.entity.ExchangeInfoHolderEntity;
import org.dev.barsukov.service.dto.ExchangeInfoHolderDto;

import java.sql.Timestamp;
import java.util.List;

public interface CrudExchangeInfoHolderService {

    ExchangeInfoHolderDto findOne(Long id);

    void delete(Long id);

    ExchangeInfoHolderDto save(ExchangeInfoHolderDto dto, String apiKey);

    ExchangeInfoHolderDto save(ExchangeInfoHolderEntity entity);

    List<ExchangeInfoHolderDto> save(List<ExchangeInfoHolderDto> dto, String apiKey);
}
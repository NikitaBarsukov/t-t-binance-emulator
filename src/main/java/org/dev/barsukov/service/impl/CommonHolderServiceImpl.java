package org.dev.barsukov.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.api.client.BinanceClient;
import org.dev.barsukov.converter.CommonHolderConverter;
import org.dev.barsukov.entity.CommonHolderEntity;
import org.dev.barsukov.enums.MockAvailableEndpoints;
import org.dev.barsukov.repository.CommonHolderRepository;
import org.dev.barsukov.service.CommonHolderService;
import org.dev.barsukov.service.dto.CommonHolderDto;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class CommonHolderServiceImpl implements CommonHolderService {
    private final CommonHolderRepository repo;
    private final CommonHolderConverter converter;
    private final BinanceClient binanceClient;

    @Override
    public CommonHolderDto addAnswer(CommonHolderDto dto, String apiKey) {
        dto.setApiKey(apiKey);
        return converter.toDto(repo.save(converter.fromDto(dto)));
    }

    @Override
    public String getExchangeInfo() {
        CommonHolderEntity answerEntity = repo.findAllByEndpointAndIsActiveTrue(MockAvailableEndpoints.EXCHANGE_INFO.getPath());
        if (answerEntity == null) {
            return binanceClient.getExchangeInfo();
        } else  {
            return answerEntity.getPayload();
        }
        //to do Invalidate cache
    }
}
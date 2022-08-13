package org.dev.barsukov.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.api.client.BinanceClient;
import org.dev.barsukov.converter.CommonHolderConverter;
import org.dev.barsukov.entity.CommonHolderEntity;
import org.dev.barsukov.entity.FailEntity;
import org.dev.barsukov.enums.MockAvailableEndpoints;
import org.dev.barsukov.repository.CommonHolderRepository;
import org.dev.barsukov.service.CommonHolderService;
import org.dev.barsukov.service.FailService;
import org.dev.barsukov.service.dto.CommonHolderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
public class CommonHolderServiceImpl implements CommonHolderService {
    private final CommonHolderRepository repo;
    private final CommonHolderConverter converter;
    private final BinanceClient binanceClient;
    private final FailService failService;

    @Override
    public CommonHolderDto addAnswer(CommonHolderDto dto, String apiKey) {
        dto.setApiKey(apiKey);
        return converter.toDto(repo.save(converter.fromDto(dto)));
    }

    @Override
    public String getExchangeInfo() {
        CommonHolderEntity answerEntity = repo.findFirstByEndpointAndIsActiveTrue(MockAvailableEndpoints.EXCHANGE_INFO.getPath());
        if (answerEntity == null) {
            return binanceClient.getExchangeInfo();
        } else  {
            return answerEntity.getPayload();
        }
    }

    @Override
    public Optional<CommonHolderDto> get(Long id) {
        return repo.findById(id).map(converter::toDto);
    }

    @Override
    public List<CommonHolderDto> getAllBy(String apiKey, String endpoint) {
        return converter.toDto(repo.findAllByEndpointAndApiKey(endpoint, apiKey));
    }

    @Override
    public CommonHolderDto updateAnswer(CommonHolderDto dto) {
        Optional<CommonHolderEntity> entity = repo.findById(dto.getId());
        if (entity.isPresent()) {
            return converter.toDto(repo.save(updateFields(dto, entity)));
        } else  {
            return null;
        }
    }

    @Override
    public void deleteAnswer(Long id) {
        repo.deleteById(id);
    }

	@Override
	public String getLeverage(String apiKey) {
        List<FailEntity> fails = failService.getActiveFailsByEndPoint(apiKey, MockAvailableEndpoints.LEVERAGE.getPath());
        if (fails.size() == 0) {
            return converter.toDto(repo.findFirstByEndpointAndApiKey(MockAvailableEndpoints.LEVERAGE.getPath(), apiKey))
                    .getPayload()
                    .toPrettyString();
        } else {
            return fails.get(0).getPayload();
        }
    }

	private CommonHolderEntity updateFields(CommonHolderDto dto, Optional<CommonHolderEntity> entity) {
        CommonHolderEntity commonHolderEntity = entity.get();
        commonHolderEntity.setEndpoint(
                dto.getEndpoint() != null
                ? dto.getEndpoint()
                : commonHolderEntity.getEndpoint()
        );
        commonHolderEntity.setPayload(
                dto.getPayload() != null
                ? dto.getPayload().toPrettyString()
                : commonHolderEntity.getPayload()
        );
        commonHolderEntity.setApiKey(
                dto.getApiKey() != null
                ? dto.getApiKey()
                : commonHolderEntity.getApiKey()
        );
        commonHolderEntity.setIsActive(
                dto.getIsActive() != null
                ? dto.getIsActive()
                : commonHolderEntity.getIsActive()
        );
        return commonHolderEntity;
    }
}
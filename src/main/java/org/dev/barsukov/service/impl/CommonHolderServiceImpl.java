package org.dev.barsukov.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.dev.barsukov.utils.MockUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
public class CommonHolderServiceImpl implements CommonHolderService {
    private final CommonHolderRepository repo;
    private final CommonHolderConverter converter;
    private final BinanceClient binanceClient;
    private final FailService failService;
    private final MockUtils mocks;
    @Qualifier("statusesCache")
    private final Map<Long, String> statusesCache;

    private final static String DOWNLOAD_PATH = "/history/files/";

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

    @Override
    public List<Object> getPositionRisk(String symbol, String apiKey) {
        int leverage = 5;
        CommonHolderEntity leverageAnswer = repo.findFirstByEndpointAndApiKey(MockAvailableEndpoints.LEVERAGE.getPath(), apiKey);
        if (leverageAnswer != null) {
             leverage = asJsonNode(leverageAnswer.getPayload())
                    .get("leverage")
                    .asInt();
        }
        return Collections.singletonList(asJsonNode(mocks.createSinglePositionRiskAnswer(leverage)));
    }

    @Override
    public Object getAccount(String apiKey) {
        CommonHolderEntity answer = repo.findFirstByEndpointAndApiKey(MockAvailableEndpoints.ACCOUNT.getPath(), apiKey);
        if (answer !=  null) {
            return asJsonNode(answer.getPayload());
        } else {
            return asJsonNode(mocks.createAccountAnswer());
        }
    }

    @Override
    public Object getRestrictions(String apiKey) {
        CommonHolderEntity answer = repo.findFirstByEndpointAndApiKey(MockAvailableEndpoints.ACCOUNT_RESTRICTIONS.getPath(), apiKey);
        if (answer !=  null) {
            return asJsonNode(answer.getPayload());
        } else {
            return asJsonNode(mocks.createApiRestrictionAnswer());
        }
    }

    @Override
    public Object getIncomeAsyncReq(String apiKey, Long id) {
        CommonHolderEntity answer = repo.findFirstByEndpointAndApiKey(MockAvailableEndpoints.ASYNC.getPath(), apiKey);
        if (answer !=  null) {
            return asJsonNode(answer.getPayload());
        } else {
            return asJsonNode(mocks.createAsyncHistoryReqAnswer(id));
        }
    }

    @Override
    public Object getIncomeAsyncByIdReq(String apiKey, Long id) {
        CommonHolderEntity answer = repo.findFirstByEndpointAndApiKey(MockAvailableEndpoints.ASYNC_ID.getPath(), apiKey);
        if (answer !=  null) {
            return asJsonNode(answer.getPayload());
        } else {

            String host = System.getenv("EMULATOR_HOST");
            if (host == null) throw new RuntimeException("env EMULATOR_HOST not found");
            String status = statusesCache.get(id);
            //If no such status we will think that is already complete.  For example, there could be a server restart
            return asJsonNode(mocks.createAsyncHistoryReqByIdAnswer(id, status != null ? status : "completed", host + DOWNLOAD_PATH + id, Instant.now().plusSeconds(3600).toEpochMilli()));
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

    private JsonNode asJsonNode(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(json);
        } catch (JsonProcessingException e) {
            log.error("Can not cast payload to JSON.", e);
        }
        return null;
    }
}
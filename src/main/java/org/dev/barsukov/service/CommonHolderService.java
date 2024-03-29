package org.dev.barsukov.service;


import org.dev.barsukov.service.dto.CommonHolderDto;

import java.util.List;
import java.util.Optional;


public interface CommonHolderService {

    CommonHolderDto addAnswer(CommonHolderDto dto, String apiKey);

    Optional<CommonHolderDto> get(Long id);

    List<CommonHolderDto> getAllBy(String apiKey, String endpoint);

    CommonHolderDto updateAnswer(CommonHolderDto dto);

    void deleteAnswer(Long id);

    String getExchangeInfo();

    String getLeverage(String apiKey);

    List<Object> getPositionRisk(String symbol, String apiKey);

    Object getAccount(String apiKey);

    Object getRestrictions(String apiKey);

    Object getIncomeAsyncReq(String apiKey, Long id);

    Object getIncomeAsyncByIdReq(String apiKey, Long id);
}
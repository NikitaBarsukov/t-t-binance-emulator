package org.dev.barsukov.service;


import org.dev.barsukov.service.dto.CommonHolderDto;


public interface CommonHolderService {

    CommonHolderDto addAnswer(CommonHolderDto dto, String apiKey);

    String getExchangeInfo();

}
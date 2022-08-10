package org.dev.barsukov.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.converter.ExchangeInfoHolderConverter;
import org.dev.barsukov.service.ExchangeInfoHolderService;
import org.dev.barsukov.service.crud.CrudExchangeInfoHolderService;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class ExchangeInfoHolderServiceImpl implements ExchangeInfoHolderService {
    private final CrudExchangeInfoHolderService crud;
    private final ExchangeInfoHolderConverter converter;


}
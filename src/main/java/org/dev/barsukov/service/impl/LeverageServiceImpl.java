package org.dev.barsukov.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.converter.LeverageConverter;
import org.dev.barsukov.service.LeverageService;
import org.dev.barsukov.service.crud.CrudLeverageService;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class LeverageServiceImpl implements LeverageService {
    private final CrudLeverageService crud;
    private final LeverageConverter converter;


}
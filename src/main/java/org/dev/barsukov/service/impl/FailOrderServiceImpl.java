package org.dev.barsukov.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.converter.CrudFailConverter;
import org.dev.barsukov.service.CrudFailService;
import org.dev.barsukov.service.crud.CrudCrudFailService;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class CrudFailServiceImpl implements CrudFailService {
    private final CrudCrudFailService crud;
    private final CrudFailConverter converter;


}
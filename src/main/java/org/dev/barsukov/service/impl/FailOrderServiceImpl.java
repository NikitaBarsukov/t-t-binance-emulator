package org.dev.barsukov.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.converter.FailOrderConverter;
import org.dev.barsukov.service.FailOrderService;
import org.dev.barsukov.service.crud.CrudFailOrderService;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class FailOrderServiceImpl implements FailOrderService {
    private final CrudFailOrderService crud;
    private final FailOrderConverter converter;


}
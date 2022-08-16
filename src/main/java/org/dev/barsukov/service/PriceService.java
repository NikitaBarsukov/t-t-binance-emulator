package org.dev.barsukov.service;


import org.dev.barsukov.service.dto.PriceDto;

import java.util.List;

public interface PriceService {

    List<PriceDto> save(List<PriceDto> dto, String apiKey);

    List<PriceDto> getAll(String apiKey, String symbol);
}

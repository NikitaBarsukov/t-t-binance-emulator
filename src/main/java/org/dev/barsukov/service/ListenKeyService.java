package org.dev.barsukov.service;

import org.dev.barsukov.service.dto.ListenKeyDto;

public interface ListenKeyService {

    ListenKeyDto generate(String dto);

    ListenKeyDto update(String apiKey);

    ListenKeyDto findOne(Long keyId);

    void invalidate(String keyId);
}

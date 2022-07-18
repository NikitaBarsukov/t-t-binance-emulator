package org.dev.barsukov.service.impl;

import lombok.AllArgsConstructor;
import org.dev.barsukov.entity.ListenKeyEntity;
import org.dev.barsukov.service.ListenKeyService;
import org.dev.barsukov.service.crud.CrudListenKeyService;
import org.dev.barsukov.service.dto.ListenKeyDto;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.Instant;

@Service
@AllArgsConstructor
public class ListenKeyServiceImpl implements ListenKeyService {
    private static final String ALPH = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int STRING_LEN = 64;
    private static final int HOUR_SEC = 3_600;

    private final CrudListenKeyService crud;

    @Override
    public ListenKeyDto generate(String apiKey) {
        ListenKeyEntity entity = new ListenKeyEntity();
        String listenKey = randomString();
        entity.setListenKey(listenKey);
        entity.setApiKey(apiKey);
        entity.setRegisterTime(Timestamp.from(Instant.now()));
        entity.setValidTime(Timestamp.from(Instant.now().plusSeconds(HOUR_SEC)));
        crud.save(entity);
        return ListenKeyDto.builder()
                .listenKey(listenKey)
                .build();
    }

    private String randomString() {
        StringBuilder sb = new StringBuilder(ListenKeyServiceImpl.STRING_LEN);
        for(int i = 0; i < ListenKeyServiceImpl.STRING_LEN; i++) {
            sb.append(ALPH.charAt(new SecureRandom().nextInt(ALPH.length())));
        }
        return sb.toString();
    }
}

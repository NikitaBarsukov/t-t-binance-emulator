package org.dev.barsukov.service.impl.crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.converter.EventHolderConverter;
import org.dev.barsukov.entity.EventHolderEntity;
import org.dev.barsukov.exception.NoSuchEventHolderException;
import org.dev.barsukov.repository.EventHolderRepository;
import org.dev.barsukov.service.crud.CrudEventHolderService;
import org.dev.barsukov.service.dto.EventHolderDto;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CrudEventHolderServiceImpl implements CrudEventHolderService {
    private final EventHolderRepository repository;
    private final EventHolderConverter converter;

    @Override
    public EventHolderDto findOne(Long id) throws JsonProcessingException {
        return converter.toDto(repository.findById(id).orElseThrow(NoSuchEventHolderException::new));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public EventHolderDto save(EventHolderDto dto) throws JsonProcessingException {
        return converter.toDto(repository.save(converter.fromDto(dto)));
    }

    @Override
    public List<JsonNode> getActiveEventsBy(String apiKey) {
        List<EventHolderEntity> eventHolders = repository.findByApiKeyAndSendOrderIsNotNull(apiKey);
        return eventHolders.stream()
                .sorted(Comparator.comparingInt(EventHolderEntity::getSendOrder))
                .map(EventHolderEntity::getEvent)
                .map(CrudEventHolderServiceImpl::toJsonNode)
                .collect(Collectors.toList());
    }

    private static JsonNode toJsonNode(String event) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonEvent = null;
        try {
            jsonEvent = mapper.readTree(event);
        } catch (JsonProcessingException e) {
            log.error("Cannon parse Event: \n" + event);
        }
        return jsonEvent;
    }

//    https://stackoverflow.com/questions/32441919/how-return-error-message-in-spring-mvc-controller
}

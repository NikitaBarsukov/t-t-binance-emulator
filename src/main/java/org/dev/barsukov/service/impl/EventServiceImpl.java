package org.dev.barsukov.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.config.websocket.WsSessionCache;
import org.dev.barsukov.entity.ListenKeyEntity;
import org.dev.barsukov.exception.NoSuchKeyException;
import org.dev.barsukov.exception.WsSendException;
import org.dev.barsukov.service.EventService;
import org.dev.barsukov.service.crud.CrudEventHolderService;
import org.dev.barsukov.service.crud.CrudListenKeyService;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
    private WsSessionCache sessions;
    private CrudEventHolderService eventCrud;
    private CrudListenKeyService listenKeyCrud;

   public List<JsonNode> putEvents(String apiKey) {
       ListenKeyEntity listenKeyEntity = listenKeyCrud.findByApiKey(apiKey);
       if (listenKeyEntity == null) {
           log.error("No active listenKey for this apiKey");
           throw new NoSuchKeyException();
       }
       log.trace("WS listenKey found {}", listenKeyEntity.getListenKey());
       List<JsonNode> events = eventCrud.getActiveEventsBy(apiKey);
       log.trace("{} events were found by this apiKey", events.size());
       WebSocketSession session = sessions.getBy(listenKeyEntity.getListenKey());
       if (session == null) {
           throw new WsSendException("No session found by this apiKey");
       }
       events.forEach(x ->  {
           try {
               session.sendMessage(new TextMessage(x.toPrettyString()));
               log.info("Event sent to {}", listenKeyEntity.getListenKey());
           } catch (Exception e) {
               log.error("Can not send Events");
               throw new WsSendException("Can not send Events", e);
           }
       });
       return events;
   }
}

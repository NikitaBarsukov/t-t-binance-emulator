package org.dev.barsukov.controller.admin;

import lombok.NoArgsConstructor;
import org.dev.barsukov.config.websocket.DummyMessage;
import org.dev.barsukov.service.dto.KeyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    public SimpMessageSendingOperations messagingTemplate;

    @PostMapping("order")
    public ResponseEntity<Object> createOrder(@RequestBody DummyMessage msg) {
        messagingTemplate.convertAndSend( "/topic/fhgjkdfhgsdfklhgdsf", msg);
        return ResponseEntity.ok(null);
    }
}

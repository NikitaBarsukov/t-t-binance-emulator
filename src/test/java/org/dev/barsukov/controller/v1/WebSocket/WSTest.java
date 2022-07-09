package org.dev.barsukov.controller.v1.WebSocket;

import org.dev.barsukov.config.websocket.DummyMessage;
import org.dev.barsukov.config.websocket.WebSocketTextHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class WSTest {
    private static String URL = "ws://localhost:8080/ws/someListenKey";

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketSession webSocketSession = client.doHandshake(new TextWebSocketHandler() {
            @Override
            public void handleTextMessage(WebSocketSession session, TextMessage message) {
                System.out.println("received message - " + message.getPayload());
            }

            @Override
            public void afterConnectionEstablished(WebSocketSession session) {
                System.out.println("established connection - " + session);
            }
        }, new WebSocketHttpHeaders(), URI.create(URL)).get();


        new Scanner(System.in).nextLine();
        webSocketSession.close();
    }

    private static DummyMessage getSampleMessage() {
        DummyMessage msg = new DummyMessage();
        msg.setFrom("Nicky");
        msg.setText("Howdy!!");
        return msg;
    }


}

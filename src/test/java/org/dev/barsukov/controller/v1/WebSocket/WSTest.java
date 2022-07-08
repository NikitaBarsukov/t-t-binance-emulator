package org.dev.barsukov.controller.v1.WebSocket;

import org.dev.barsukov.config.websocket.DummyMessage;
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
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class WSTest {
    private static String URL = "ws://localhost:8080/hello";

    public static void main(String[] args) {
        WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                System.out.println("New session established : " + session.getSessionId());
                session.subscribe("/topic/fhgjkdfhgsdfklhgdsf", this);
                System.out.println("Subscribed to /topic/fhgjkdfhgsdfklhgdsf");
                session.send("/app/chat", getSampleMessage());
                System.out.println("Message sent to websocket server");
            }

            @Override
            public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
                System.out.println("Got an exception" + exception.getMessage());
            }

            @Override
            public Type getPayloadType(StompHeaders headers) {
                return DummyMessage.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                DummyMessage msg = (DummyMessage) payload;
                System.out.println("Received : " + msg.getText() + " from : " + msg.getFrom());
            }
        };
        stompClient.connect(URL, sessionHandler);
        new Scanner(System.in).nextLine();
    }

    private static DummyMessage getSampleMessage() {
        DummyMessage msg = new DummyMessage();
        msg.setFrom("Nicky");
        msg.setText("Howdy!!");
        return msg;
    }
}

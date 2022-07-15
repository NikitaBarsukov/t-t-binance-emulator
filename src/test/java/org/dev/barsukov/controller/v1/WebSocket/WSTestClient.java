package org.dev.barsukov.controller.v1.WebSocket;

import org.dev.barsukov.config.websocket.DummyMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class WSTestClient {
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

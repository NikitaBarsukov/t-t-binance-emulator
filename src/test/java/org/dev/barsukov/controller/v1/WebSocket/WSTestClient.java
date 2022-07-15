package org.dev.barsukov.controller.v1.WebSocket;

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
    private static String URL = "ws://localhost:8080/ws/8IFKZs18kYjTZJl4T6JR8d8cHjAyfSnwAx5C2RRLjNrdGVvtgRNqM0pOQKB1iu6y";

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
}

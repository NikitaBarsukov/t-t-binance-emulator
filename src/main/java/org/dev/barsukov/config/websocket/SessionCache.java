package org.dev.barsukov.config.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

@Component
public class SessionCache {
    private List<WebSocketSession> sessions = new ArrayList();


    public void put(WebSocketSession session) {
        sessions.add(session);
    }

    public List<WebSocketSession> getSessions() {
        return sessions;
    }
}

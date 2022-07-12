package org.dev.barsukov.config.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
public class SessionCache {
    private final Map<String, WebSocketSession> sessions = new HashMap();

    public void put(WebSocketSession session) {
        sessions.put(extractId(session.getUri()), session);
    }

    public WebSocketSession get(String id) {
        return sessions.get(id);
    }

    public WebSocketSession remove(String id) {
        return sessions.remove(id);
    }

    private String extractId(URI uri) {
        String[] segments = uri.getPath().split("/");
        return segments[segments.length - 1];
    }
}

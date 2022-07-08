package org.dev.barsukov.config.websocket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String from;
    private String text;
}

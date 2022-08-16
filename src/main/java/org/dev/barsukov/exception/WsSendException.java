package org.dev.barsukov.exception;

public class WsSendException extends RuntimeException {
    public WsSendException(String str, Throwable e) {
        super(str, e);
    }

    public WsSendException(String str) {
        super(str);
    }
}

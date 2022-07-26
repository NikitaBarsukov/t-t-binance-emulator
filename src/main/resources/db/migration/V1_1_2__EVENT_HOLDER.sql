CREATE TABLE IF NOT EXISTS EVENT_HOLDER
(
    ID         BIGSERIAL,
    SESSION_ID VARCHAR(255),
    API_KEY    VARCHAR(255),
    SECRET_KEY VARCHAR(255),
    SEND_ORDER INT,
    EVENT      TEXT
)
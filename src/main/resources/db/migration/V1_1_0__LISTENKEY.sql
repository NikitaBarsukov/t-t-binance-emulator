CREATE TABLE IF NOT EXISTS LISTENKEY (
    ID BIGSERIAL,
    KEY VARCHAR(255),
    SECRET VARCHAR(255),
    UNIQUE(ID)
)
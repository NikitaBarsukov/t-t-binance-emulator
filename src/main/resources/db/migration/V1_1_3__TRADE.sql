CREATE TABLE IF NOT EXISTS TRADE
(
    ID               BIGSERIAL,
    BUYER            BOOLEAN,
    COMMISSION       VARCHAR(255),
    COMMISSION_ASSET VARCHAR(255),
    MAKER            BOOLEAN,
    ORDER_ID         BIGINT,
    PRICE            VARCHAR(255),
    QTY              VARCHAR(255),
    QUOTE_QTY        VARCHAR(255),
    REALIZED_PNL     VARCHAR(255),
    SIDE             VARCHAR(255),
    POSITION_SIDE    VARCHAR(255),
    SYMBOL           VARCHAR(255),
    API_KEY          VARCHAR(255),
    TIME             TIMESTAMP,
    UNIQUE (ID)

)

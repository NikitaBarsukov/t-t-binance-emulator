CREATE TABLE IF NOT EXISTS COMMON_HOLDER
(
    ID        BIGSERIAL,
    IS_ACTIVE BOOLEAN,
    ENDPOINT VARCHAR(255),
	API_KEY   VARCHAR(255),
	PAYLOAD   TEXT,
    UNIQUE (ID)
)

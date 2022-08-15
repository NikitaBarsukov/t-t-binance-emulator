## Requirements

- Java 17;
- PostgreSQL. Develop on 14.4.

## Build

org.gradle.jvmargs=-XX:MaxPermSize=512m -Xmx2048m First of all **JAVA_HOME** must be set in order to use **Gradle 7.5.1** for app building. In project root use
command to start the build:

## Using

For all endpoint 
| Method    | ENDPOINT      | Action                                                                   |
|-----------|---------------|--------------------------------------------------------------------------|
| `POST`    | admin/history | All transactions are saved in the database for the specified user        |
| `POST`    | admin/order   | Create order. If websocket already opened sends a trade there            |
| `POST`    | admin/price   | Create order. If websocket already opened sends a trade there            |
| `POST`    | admin/fail    | Creates an error for the next endpoint calls                             |
| `POST`    | admin/mock    | Creates a response that will be returned for the next endpoint calls     |

### Mockable endpoints

The list of the mockable endpoints:

| Method | ENDPOINT                         |
|--------|----------------------------------|
| `GET`  | /fapi/v1/exchangeInfo            |
| `GET`  | /fapi/v1/leverage                |
| `GET`  | /fapi/v2/positionRisk            |
| `GET`  | /sapi/v1/account/apiRestrictions |
| `GET`  | /fapi/v1/income/asyn/            |
| `GET`  | /fapi/v1/income/asyn/id          |

## Requirements

- Java 17;
- PostgreSQL. Developed on 14.4.

## Build

First of all **JAVA_HOME** must be set in order to use **Gradle 7.5.1** for app building.

Secondly, set all spring.datasource* props in `application.properties`.

## Run
In project root use command to start the Spring Boot App:

`./gradlew bootRun`

If you're facing with deprecated `XX:MaxPermSize=512m ` error, check your `gradle.properties` in `.gradle` in you profile-folder.   
Comment `org.gradle.jvmargs=-XX:MaxPermSize=512m -Xmx2048m`

## Migrations

Migration should autorun on first App run. if it didn't happen you can run migrations by:

`./gradlew flywayMigrate`

Don't forget set props in `build.gradle` for migration Task using

```
flyway {
    url = 'jdbc:postgresql://localhost:5432/postgres'
    user = 'postgres'
    password = 'postgres'
    schemas = ''
}
```
Migration folder `src/main/resources/db/migration`

## Using

All endpoints need a header `X-MBX-APIKEY`. The value is any 255-length string.
The only exception is /fapi/v1/exchangeInfo. Header can be omitted there.
The table of admin endpoints:

| Method    | ENDPOINT      | Action                                                               |
|-----------|---------------|----------------------------------------------------------------------|
| `POST`    | admin/history | Creates transactions                                                 |
| `POST`    | admin/order   | Creates order. If websocket already opened sends a trade there       |
| `POST`    | admin/events  | Creates user events for WS sending                                   |
| `POST`    | admin/price   | Creates prices                                                       |
| `POST`    | admin/fail    | Creates an error for the next endpoint calls                         |
| `POST`    | admin/mock    | Creates a response that will be returned for the next endpoint calls |

### Mockable endpoints
Mockable endpoints are a special type of endpoints which handles by admin/mock. 
They are differing in a special representation in the database: Some meta info and payload - text answer.

| Attribute   | Description                                                                             |
|-------------|-----------------------------------------------------------------------------------------|
| id          | Id in DB                                                                                |
| endpoint    | Endpoint to send stored answer                                                          |
| payload     | Answer. It must be valid JSON                                                           |
| apiKey      | Stored X-MBX-APIKEY. Identifies the user to respond to                                  |
| isActive    | The answer can be disabled so as not to be deleted from the database. By default = true |

Example how to mock mockable endpoint:

```
POST  http://{{host}}/admin/mock
X-MBX-APIKEY: vmPUZE6mv9SD5VNHk4HlWFsOr6aKE2zvsw0MuIgwCIPy6utIco14y7Ju91duEh8A
Content-Type: application/json
```

```json
{
   "endpoint": "leverage",
   "payload": {
   "leverage": 21,
   "maxNotionalValue": "1000000",
   "symbol": "BTCUSDT"
   }
}
```
***See test/e2e/MockService/FastFill.http** 

The table of the mockable endpoints:

| Method | ENDPOINT                         | Available url params |
|--------|----------------------------------|----------------------|
| `GET`  | /fapi/v1/exchangeInfo            | -                    |
| `GET`  | /fapi/v1/leverage                | -                    |
| `GET`  | /fapi/v2/account                 | -                    |
| `GET`  | /sapi/v1/account/apiRestrictions | -                    |
| `GET`  | /fapi/v1/income/asyn/            | startTime;endTime    |
| `GET`  | /fapi/v1/income/asyn/id          | downloadId           |      


## Another endpoints
Another type of endpoints differs by being stored in SQL's first normal form (Value per cell) to make it easier to work with it.

The table of such endpoints answers:

| Method | ENDPOINT                    | Available url params                              |
|--------|-----------------------------|---------------------------------------------------|
| `GET`  | /fapi/v1/ticker/price       | symbol                                            |
| `GET`  | /fapi/v1/order              | symbol;orderId;clientOrderId                      |
| `GET`  | /fapi/v1/income             | symbol;incomeType;startTime;endTime;limit         |
| `GET`  | /fapi/v1/allOrders          | symbol;orderId;incomeType;startTime;endTime;limit |
| `GET`  | /fapi/v1/userTrades         | startTime;endTime                                 |

## Non-mockable

`GET /fapi/v2/positionRisk`
Non-mockable endpoint. Hardcoded in sourcecode. By default, creates leverage = 5. If we have a response for endpoint `leverage`  gets leverage from it.

## Websockets

| Method   | ENDPOINT             | Description                                         |
|----------|----------------------|-----------------------------------------------------|
| `POST`   | /fapi/v1/listenKey   | Generate a listenKey (random string) with meta-info |
| `PUT`    | /fapi/v1/listenKey   | Update listenKey expiration time                    |
| `DELETE` | /fapi/v1/listenKey   | Deletes user listenKey                              |
*See `src/test/e2e/ListenKeyLifecycle/ListenKey.http`

WebSocket gate available on `ws://<host>/ws/<listenKey>`

Sent Events can be set by `admin/events`

*See more `src/test/e2e/EventHolderLifecycle/EventHolder.http`

Test Client available on `src/test/java/org/dev/barsukov/controller/v1/WebSocket/WSTestClient.java`

## /fapi/v1/income/asyn
To work with async history creation you need to set OS environment variables:
- `EMULATOR_HOST` - service host for async/id JSON answer attribute `url`;
- `EMULATOR_HISTORY_DIR_ABS` - folder where user history file will be kept.

## Failable-endpoints
It's endpoints whose answer can be overridden by endpoint `/admin/fail` regardless of availability any answer in `admin/mock`.
If there is a record belongs to user in the `admin/fails` DB, the answer from `/fail` will always be returned first.
Fails checked in these endpoints:
- `/order`
- `/leverage`

*See more src/test/e2e/FailHolderLifecycle/FailHolder.http


## The e2e folder

The e2e folder located by /test. It contains e2e-tests for most of the used endpoints. 
This is written using IDEA HttpClient which is easy to embed in Intelij IDEA.
* See https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html

## Swagger 
Some swagger UI available on `/swagger-ui.html`.
## Requirements

- Java 17;
- PostgreSQL. Developed on 14.4.

## Build

org.gradle.jvmargs=-XX:MaxPermSize=512m -Xmx2048m First of all **JAVA_HOME** must be set in order to use **Gradle 7.5.1** for app building. In project root use
command to start the build:

## Using

All endpoints need a header `X-MBX-APIKEY`. The value is any 255-length string.
The only exception is /fapi/v1/exchangeInfo. It can be omitted there.
The table of admin endpoints:

| Method    | ENDPOINT      | Action                                                               |
|-----------|---------------|----------------------------------------------------------------------|
| `POST`    | admin/history | Creates transactions                                                 |
| `POST`    | admin/order   | Creates order. If websocket already opened sends a trade there       |
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
***See test/e2e/MockService/** 

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
Another type of endpoints differs by being stored in SQL's first normal form (Value per cell). 


GET /fapi/v1/ticker/price  Binance API Documentation Цены конфигурировать нужно через служебное апи
GET /fapi/v1/order  Binance API Documentation поиск ордера (для случаев когда создание было неуспешным). Нужна возможность подставлять ответы через служебное апи
GET /fapi/v1/income  Binance API Documentation запрос на получение  истории (ограничивается 3 месяцами)
GET /fapi/v1/allOrders  Binance API Documentation  данные обо всех ордерах пользователя. Отдаем только то что создали через 5 и через служебное апи
GET /fapi/v1/userTrades  Binance API Documentation  трейды пользователя. Отдаем только то что создали через 5 и через служебное апи



GET /fapi/v2/positionRisk  Binance API Documentation используем для получения данных о позиции. Отдаем leverage позиций пользователя, если его никто не менял, то по умолчанию отдаем 5

POST /fapi/v1/listenKey  Binance API Documentation  запрос на получение ключа и открытия потока данных пользователя (WS)
PUT /fapi/v1/listenKey  Binance API Documentation  keepalive запрос для потока данных пользователя (WS)
DELETE /fapi/v1/listenKey  Binance API Documentation  закрыть поток данных пользователя (WS)


/fapi/v1/income/asyn/id

Failable
Migrations run
application.prop

localhost:8080/swagger-ui.html
package org.dev.barsukov.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.config.websocket.WsSessionCache;
import org.dev.barsukov.entity.ListenKeyEntity;
import org.dev.barsukov.entity.TradeEntity;
import org.dev.barsukov.exception.NoSuchKeyException;
import org.dev.barsukov.exception.WsSendException;
import org.dev.barsukov.service.EventService;
import org.dev.barsukov.service.crud.CrudEventHolderService;
import org.dev.barsukov.service.crud.CrudListenKeyService;
import org.dev.barsukov.service.crud.CrudOrderService;
import org.dev.barsukov.service.crud.CrudTradeService;
import org.dev.barsukov.service.dto.OrderDto;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
    private final WsSessionCache sessions;
    private final CrudEventHolderService eventCrud;
    private final CrudListenKeyService listenKeyCrud;
    private final CrudTradeService tradeCrud;
    private final CrudOrderService orderCrud;

   public List<JsonNode> putEvents(OrderDto dto, String apiKey) {
       dto.setSessionId(apiKey);
       orderCrud.save(dto);
       log.trace("Order saved");
       ListenKeyEntity listenKeyEntity = listenKeyCrud.findActualByApiKey(apiKey);
       if (listenKeyEntity == null) {
           log.error("No active listenKey for this apiKey");
           throw new NoSuchKeyException();
       }
       log.trace("WS listenKey found {}", listenKeyEntity.getListenKey());
       WebSocketSession session = sessions.getBy(listenKeyEntity.getListenKey());
       if (session == null) {
           throw new WsSendException("No session found by this apiKey");
       }
       List<JsonNode> events = eventCrud.getActiveEventsBy(apiKey);
       log.trace("{} events were found by this apiKey", events.size());
       events.forEach(event ->  {
           try {
               session.sendMessage(new TextMessage(event.toPrettyString()));
               log.info("Event sent to {}", listenKeyEntity.getListenKey());
               if (isFilledTrade(event)) {
                   TradeEntity tradeEntity = tradeFrom(event, dto, apiKey);
                   if (tradeEntity != null) {
                       tradeCrud.save(tradeEntity);
                   }
               }
           } catch (Exception e) {
               log.error("Can not send Events");
               throw new WsSendException("Can not send Events", e);
           }
       });
       return events;
   }

/**
    {
        "buyer": false, // side != "SELL"
        "commission": "-0.07819010", //  ORDER_TRADE_UPDATE поле "n"  -quoteQty / 200
        "commissionAsset": "USDT", //  ORDER_TRADE_UPDATE поле "N" по умолчанию всегда USDT
        "id": 698759,//  ORDER_TRADE_UPDATE поле "t" - рандом
        "maker": false, // всегда
        "orderId": 25851813,//  ORDER_TRADE_UPDATE поле "i" - рандом
        "price": "7819.01",//  ORDER_TRADE_UPDATE поле "ap" - если не передана берем из текущих цен
        "qty": "0.002",//  ORDER_TRADE_UPDATE поле "q" - из ордера
        "quoteQty": "15.63802", // price * qty
        "realizedPnl": "-0.91539999", //  ORDER_TRADE_UPDATE поле "rp"
        "side": "SELL", //  ORDER_TRADE_UPDATE поле "S" - из ордера
        "positionSide": "BOTH", // всегда
        "symbol": "BTCUSDT", // ORDER_TRADE_UPDATE поле "s"
        "time": 1569514978020, // ORDER_TRADE_UPDATE поле "T"
   }
 **/
    private TradeEntity tradeFrom(JsonNode event, OrderDto dto, String apiKey) {
        TradeEntity trade = new TradeEntity();
        JsonNode T = event.get("T");
        trade.setTime(T != null ? new Timestamp(T.asLong()) : null);
        JsonNode o = event.get("o");
        if (o == null) {
            log.error("Can not create trade cause order's 'o' is null");
            return null;
        }
        trade.setApiKey(apiKey);
        if (dto.getSide() != null) {
            trade.setBuyer(!dto.getSide().equals("SELL"));
        }
        JsonNode q = o.get("q");
        if (q != null) {
            trade.setQty(q.asText());
            String orderPrice = dto.getPrice();
            Double quoteQty = orderPrice != null
                              ? Double.parseDouble(orderPrice) * q.asDouble()
                              : null;
            trade.setQuoteQty(String.valueOf(quoteQty));
            JsonNode n = o.get("n");
            trade.setCommission(n != null && quoteQty != null
                                ? String.valueOf(n.asDouble() - (quoteQty / 200))
                                : null);
        } else {
            log.error("Can not calculate Trade prices cause o q is null");
        }
        JsonNode N = o.get("N");
        trade.setCommissionAsset(N != null ? N.asText() : "USDT");
        JsonNode t = o.get("t");
        trade.setId(t != null ? t.asLong() : null);
        JsonNode i = o.get("i");
        trade.setOrderId(i != null ? i.asLong() : 2133123 + (new Random().nextLong() * (2133123213 - 2133123)));
        JsonNode ap = o.get("ap");
        trade.setPrice(ap != null ? ap.asText() : null);
        JsonNode rp = o.get("rp");
        trade.setRealizedPnl(rp != null ? rp.asText() : null);
        JsonNode S = o.get("S");
        trade.setSide(S != null ? S.asText() : null);
        JsonNode s = o.get("s");
        trade.setSymbol(s != null ? s.asText() : null);
        trade.setMaker(false);
        trade.setPositionSide("BOTH");
        return trade;
    }


    private boolean isFilledTrade(JsonNode event) {
        JsonNode e = event.get("e");
        if (e != null && e.asText().equals("ORDER_TRADE_UPDATE")) {
            JsonNode o = event.get("o");
            if (o == null) {
                return false;
            } else {
                JsonNode X = o.get("X");
                JsonNode x = o.get("x");
                if (x == null || X == null ) {
                    return false;
                }
                return x.asText().equals("TRADE") && X.asText().equals("FILLED");
            }
        } else {
            return false;
        }
    }
}



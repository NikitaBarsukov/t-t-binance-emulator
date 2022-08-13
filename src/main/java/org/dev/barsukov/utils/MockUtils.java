package org.dev.barsukov.utils;


import org.springframework.stereotype.Component;

/**
 * Holds the default Answers for endpoints
 */
@Component
public class MockUtils {

    public String createSinglePositionRiskAnswer(int leverage) {
        return String.format("""
                   {
                        "entryPrice": "0.00000",
                        "marginType": "isolated",\s
                        "isAutoAddMargin": "false",
                        "isolatedMargin": "0.00000000",\s
                        "liquidationPrice": "0",\s
                        "leverage": %d,\s
                        "markPrice": "6679.50671178",  \s
                        "maxNotionalValue": "20000000",\s
                        "positionAmt": "0.000",
                        "notional": "0",\s
                        "isolatedWallet": "0",
                        "symbol": "BTCUSDT",\s
                        "unRealizedProfit": "0.00000000",\s
                        "positionSide": "BOTH",
                        "updateTime": 0
                    }
                """, leverage);
    }

    public String createApiRestrictionAnswer() {
        return """
                {
                   "ipRestrict": false,
                   "createTime": 1623840271000,
                   "enableWithdrawals": false,
                   "enableInternalTransfer": true,
                   "permitsUniversalTransfer": true,
                   "enableVanillaOptions": false,
                   "enableReading": true,
                   "enableFutures": false,
                   "enableMargin": false,
                   "enableSpotAndMarginTrading": false,
                   "tradingAuthorityExpirationTime": 1628985600000
                }
                """;
    }

    public String createAccountAnswer() {
        String totalWalletBalance = "0.00000000";
        String totalMarginBalance = "0.00000000";
        String totalCrossWalletBalance = "0.00000000";
        String availableBalance = "0.00000000";
        String maxWithdrawAmount = "0.00000000";
        return String.format(
                """
                {
                  "feeTier": 0,
                  "canTrade": true,
                  "canDeposit": true,
                  "canWithdraw": true,
                  "updateTime": 0,
                  "totalInitialMargin": "0.00000000",
                  "totalMaintMargin": "0.00000000",
                  "totalWalletBalance": "%s",
                  "totalUnrealizedProfit": "0.00000000",
                  "totalMarginBalance": "%s",
                  "totalPositionInitialMargin": "0.00000000",
                  "totalOpenOrderInitialMargin": "0.00000000",
                  "totalCrossWalletBalance": "%s",
                  "totalCrossUnPnl": "0.00000000",
                  "availableBalance": "%s",
                  "maxWithdrawAmount": "%s",
                  "assets": [
                    {
                      "asset": "USDT",
                      "walletBalance": "23.72469206",
                      "unrealizedProfit": "0.00000000",
                      "marginBalance": "23.72469206",
                      "maintMargin": "0.00000000",
                      "initialMargin": "0.00000000",
                      "positionInitialMargin": "0.00000000",
                      "openOrderInitialMargin": "0.00000000",
                      "crossWalletBalance": "23.72469206",
                      "crossUnPnl": "0.00000000",
                      "availableBalance": "23.72469206",
                      "maxWithdrawAmount": "23.72469206",
                      "marginAvailable": true,
                      "updateTime": 1625474304765
                    },
                    {
                      "asset": "BUSD",
                      "walletBalance": "103.12345678",
                      "unrealizedProfit": "0.00000000",
                      "marginBalance": "103.12345678",
                      "maintMargin": "0.00000000",
                      "initialMargin": "0.00000000",
                      "positionInitialMargin": "0.00000000",
                      "openOrderInitialMargin": "0.00000000",
                      "crossWalletBalance": "103.12345678",
                      "crossUnPnl": "0.00000000",
                      "availableBalance": "103.12345678",
                      "maxWithdrawAmount": "103.12345678",
                      "marginAvailable": true,
                      "updateTime": 1625474304765
                    }
                  ],
                  "positions": [
                    {
                      "symbol": "BTCUSDT",
                      "initialMargin": "0",
                      "maintMargin": "0",
                      "unrealizedProfit": "0.00000000",
                      "positionInitialMargin": "0",
                      "openOrderInitialMargin": "0",
                      "leverage": "100",
                      "isolated": true,
                      "entryPrice": "0.00000",
                      "maxNotional": "250000",
                      "bidNotional": "0",
                      "askNotional": "0",
                      "positionSide": "BOTH",
                      "positionAmt": "0",
                      "updateTime": 0
                    }
                  ]
                }
                """,
                totalWalletBalance,
                totalMarginBalance,
                totalCrossWalletBalance,
                availableBalance,
                maxWithdrawAmount
        );
    }

}

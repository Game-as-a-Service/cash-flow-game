package tw.waterball.cashflow.domain.entity.event;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.domain.entity.FinancialItem;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;
import tw.waterball.cashflow.domain.entity.exception.InsufficientSharesException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class StockEventTest {

    // 買股票，沒有現金流
    @Test
    void giveStockEventTypeWithNoCashFlow_whenStockEventAndBuyStock_thenSubtractCaseAndAndAddAsset() throws InsufficientCashException {
        // Given
        Actor actor = new Actor("actor_a", Career.ENGINEER);
        FinancialStatementV2 financialStatement = actor.getFinancialStatementV2();
        financialStatement.addCash(BigDecimal.valueOf(3000000));
        BigDecimal originalCash = financialStatement.getCash();
        BigDecimal originalAsset = financialStatement.getAsset().getTotalAssetAmount();
        int share = 10;

        // When 假裝抽到 STOCK_OK4U_20
        Event stockEvent = EventFactory.getStockEvent(StockEventType.STOCK_OK4U_20);
        Map<String, Object> param = new HashMap<>();
        param.put("isBuy", true);
        param.put("shares", share);
        stockEvent.execute(actor, param);

        // Then
        // 現金減少
        Assertions.assertEquals(StockEventType.STOCK_OK4U_20.getCost().multiply(BigDecimal.valueOf(share)), originalCash.subtract(financialStatement.getCash()));
        // 資產增加
        Assertions.assertEquals(StockEventType.STOCK_OK4U_20.getCost().multiply(BigDecimal.valueOf(share)), financialStatement.getAsset().getTotalAssetAmount().subtract(originalAsset));
    }

    // 買股票，有現金流
    @Test
    void giveStockEventTypeWithCashFlow_whenStockEventAndBuyStock_thenSubtractCaseAndAddIncomeAndAddAsset() throws InsufficientCashException {
        // Given
        Actor actor = new Actor("actor_a", Career.ENGINEER);
        FinancialStatementV2 financialStatement = actor.getFinancialStatementV2();
        financialStatement.addCash(BigDecimal.valueOf(3000000));
        BigDecimal originalCash = financialStatement.getCash();
        BigDecimal originalIncome = financialStatement.getIncome().getTotalIncomeAmount();
        BigDecimal originalAsset = financialStatement.getAsset().getTotalAssetAmount();
        int share = 10;

        // When 假裝抽到 STOCK_ON2U
        Event stockEvent = EventFactory.getStockEvent(StockEventType.STOCK_ON2U);
        Map<String, Object> param = new HashMap<>();
        param.put("isBuy", true);
        param.put("shares", share);
        stockEvent.execute(actor, param);

        // Then
        // 現金減少
        Assertions.assertEquals(StockEventType.STOCK_ON2U.getCost().multiply(BigDecimal.valueOf(share)), originalCash.subtract(financialStatement.getCash()));
        // 現金流增加
        Assertions.assertEquals(StockEventType.STOCK_ON2U.getCashFlow().multiply(BigDecimal.valueOf(share)), financialStatement.getIncome().getTotalIncomeAmount().subtract(originalIncome));
        // 資產增加
        Assertions.assertEquals(StockEventType.STOCK_ON2U.getCost().multiply(BigDecimal.valueOf(share)), financialStatement.getAsset().getTotalAssetAmount().subtract(originalAsset));
    }

    // 買股票，但金額不足
    @Test
    void giveCostBiggerThenCash_whenStockEventAndBuyStock_thenThrowInsufficientCashException() {
        // Given
        Actor actor = new Actor("actor_a", Career.ENGINEER);
        BigDecimal currentCash = actor.getFinancialStatementV2().getCash();

        // When 假裝抽到 STOCK_ON2U
        Event stockEvent = EventFactory.getStockEvent(StockEventType.STOCK_ON2U);
        Map<String, Object> param = new HashMap<>();
        param.put("isBuy", true);
        param.put("shares", 999999999);

        // When,Then
        Assertions.assertThrows(InsufficientCashException.class, () -> stockEvent.execute(actor, param));
    }

    // 賣股票，沒有現金流
    @Test
    void giveStockEventTypeWithNoCashFlow_whenStockEventAndSellStock_thenAddCaseAndSubtractIncomeSubtractAddAsset() throws InsufficientCashException {
        // Given
        Actor actor = new Actor("actor_a", Career.ENGINEER);
        FinancialStatementV2 financialStatement = actor.getFinancialStatementV2();
        StockEventType stockOn2u = StockEventType.STOCK_OK4U_40;
        String itemId = stockOn2u.name() + "-" + System.currentTimeMillis();
        int shares = 2;
        financialStatement.getIncome().addDividend(FinancialItem.builder(itemId, stockOn2u.getName(), stockOn2u.getCashFlow()).count(shares).build());
        financialStatement.getAsset().addStock(FinancialItem.builder(itemId, stockOn2u.getName(), stockOn2u.getCost()).count(shares).build());
        BigDecimal originalCash = financialStatement.getCash();
        BigDecimal originalIncome = financialStatement.getIncome().getTotalIncomeAmount();
        BigDecimal originalAsset = financialStatement.getAsset().getTotalAssetAmount();

        // When 假裝抽到 STOCK_ON2U
        Event stockEvent = EventFactory.getStockEvent(stockOn2u);
        Map<String, Object> param = new HashMap<>();
        param.put("isBuy", false);
        param.put("shares", shares);
        param.put("itemId", itemId);
        stockEvent.execute(actor, param);

        // Then
        // 現金增加
        Assertions.assertEquals(stockOn2u.getCost().multiply(BigDecimal.valueOf(shares)), financialStatement.getCash().subtract(originalCash));
        // 資產減少
        Assertions.assertEquals(stockOn2u.getCost().multiply(BigDecimal.valueOf(shares)), originalAsset.subtract(financialStatement.getAsset().getTotalAssetAmount()));
    }

    // 賣股票，有現金流
    @Test
    void giveStockEventTypeWithCashFlow_whenStockEventAndSellStock_thenAddCaseAndSubtractIncomeSubtractAddAsset() throws InsufficientCashException {
        // Given
        Actor actor = new Actor("actor_a", Career.ENGINEER);
        FinancialStatementV2 financialStatement = actor.getFinancialStatementV2();
        StockEventType stockOn2u = StockEventType.STOCK_ON2U;
        String itemId = stockOn2u.name() + "-" + System.currentTimeMillis();
        int shares = 2;
        financialStatement.getIncome().addDividend(FinancialItem.builder(itemId, stockOn2u.getName(), stockOn2u.getCashFlow()).count(shares).build());
        financialStatement.getAsset().addStock(FinancialItem.builder(itemId, stockOn2u.getName(), stockOn2u.getCost()).count(shares).build());
        BigDecimal originalCash = financialStatement.getCash();
        BigDecimal originalIncome = financialStatement.getIncome().getTotalIncomeAmount();
        BigDecimal originalAsset = financialStatement.getAsset().getTotalAssetAmount();

        // When 假裝抽到 STOCK_ON2U
        Event stockEvent = EventFactory.getStockEvent(stockOn2u);
        Map<String, Object> param = new HashMap<>();
        param.put("isBuy", false);
        param.put("shares", shares);
        param.put("itemId", itemId);
        stockEvent.execute(actor, param);

        // Then
        // 現金增加
        Assertions.assertEquals(stockOn2u.getCost().multiply(BigDecimal.valueOf(shares)), financialStatement.getCash().subtract(originalCash));
        // 現金流減少
        Assertions.assertEquals(stockOn2u.getCashFlow().multiply(BigDecimal.valueOf(shares)), originalIncome.subtract(financialStatement.getIncome().getTotalIncomeAmount()));
        // 資產減少
        Assertions.assertEquals(stockOn2u.getCost().multiply(BigDecimal.valueOf(shares)), originalAsset.subtract(financialStatement.getAsset().getTotalAssetAmount()));
    }

    // 股數不夠
    @Test
    void giveSharesBiggerThenHave_whenStockEventAndBuyStock_thenThrowException() throws InsufficientCashException {
        // Given
        Actor actor = new Actor("actor_a", Career.ENGINEER);
        FinancialStatementV2 financialStatement = actor.getFinancialStatementV2();
        StockEventType stockOn2u = StockEventType.STOCK_ON2U;
        String itemId = stockOn2u.name() + "-" + System.currentTimeMillis();
        int shares = 2;
        financialStatement.getIncome().addDividend(FinancialItem.builder(itemId, stockOn2u.getName(), stockOn2u.getCashFlow()).count(shares-1).build());
        financialStatement.getAsset().addStock(FinancialItem.builder(itemId, stockOn2u.getName(), stockOn2u.getCost()).count(shares-1).build());

        // When 假裝抽到 STOCK_ON2U
        Event stockEvent = EventFactory.getStockEvent(StockEventType.STOCK_ON2U);
        Map<String, Object> param = new HashMap<>();
        param.put("isBuy", false);
        param.put("shares", shares);
        param.put("itemId", itemId);

        // When,Then
        Assertions.assertThrows(InsufficientSharesException.class, () -> stockEvent.execute(actor, param));
    }

}

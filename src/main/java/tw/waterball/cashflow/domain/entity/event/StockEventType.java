package tw.waterball.cashflow.domain.entity.event;

import lombok.Getter;
import tw.waterball.cashflow.domain.entity.FinancialItemName;

import java.math.BigDecimal;


public enum StockEventType {
    STOCK_OK4U_40(FinancialItemName.STOCK_OK4U, 40, 0, "$5 to $40"),
    STOCK_OK4U_20(FinancialItemName.STOCK_OK4U, 20, 0, "$5 to $40"),
    STOCK_OK4U_5(FinancialItemName.STOCK_OK4U, 5, 0, "$5 to $40"),
    STOCK_ON2U(FinancialItemName.STOCK_ON2U, 1200, 10, "$1,200 to $1,200");

    /**
     * 股票代碼
     */
    @Getter
    private final FinancialItemName name;

    /**
     * 成本
     */
    @Getter
    private final BigDecimal cost;
    /**
     * 月現金流
     */
    @Getter
    private final BigDecimal cashFlow;

    /**
     * 交易區間
     */
    @Getter
    private final String tradingRange;

    StockEventType(FinancialItemName name, long cost, long cashFlow, String tradingRange) {
        this.name = name;
        this.cost = BigDecimal.valueOf(cost);
        this.cashFlow = BigDecimal.valueOf(cashFlow);
        this.tradingRange = tradingRange;
    }

}

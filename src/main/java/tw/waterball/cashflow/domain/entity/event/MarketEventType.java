package tw.waterball.cashflow.domain.entity.event;

import java.math.BigDecimal;

public enum MarketEventType {
    /**
     * 通貨膨脹
     */
    INFLATION_HIT(65000),
    HOUSE_BUYER(65000);

    private final BigDecimal amount;


    MarketEventType(long amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    /**
     * @return 此事件的售出金額
     */
    public BigDecimal getAmount() {
        return this.amount;
    }
}

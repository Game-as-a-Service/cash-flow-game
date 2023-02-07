package tw.waterball.cashflow.domain.entity.event;

import java.math.BigDecimal;

/**
 * 額外支出的種類
 *
 * @see EventType
 */
public enum ExtraPaymentEventType {
    /**
     * 額外支出 - 結婚紀念日
     */
    ANNIVERSARY(6000),
    /**
     * 額外支出 - 購買電視
     */
    TELEVISION(5000),
    /**
     * 額外支出 - 度假
     */
    TRAVEL(12000),
    /**
     * 額外支出 - 醫療
     */
    HOSPITAL(360),
    /**
     * 額外支出 - 新手機
     */
    PHONE(10000);

    private final BigDecimal amount;

    ExtraPaymentEventType(long amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    /**
     * @return 回傳該額外支出事件的付款金額
     */
    public BigDecimal getAmount() {
        return amount;
    }
}

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
    Anniversary(6000),
    /**
     * 額外支出 - 購買電視
     */
    Television(5000),
    /**
     * 額外支出 - 度假
     */
    Travel(12000),
    /**
     * 額外支出 - 醫療
     */
    Hospital(360),
    /**
     * 額外支出 - 新手機
     */
    Phone(10000);

    private final BigDecimal payment;
    ExtraPaymentEventType(long payment)
    {
        this.payment = BigDecimal.valueOf(payment);
    }

    public BigDecimal getPayment() {
        return payment;
    }
}

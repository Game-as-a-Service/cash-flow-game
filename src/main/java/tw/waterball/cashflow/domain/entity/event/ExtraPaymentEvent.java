package tw.waterball.cashflow.domain.entity.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.ToString;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 額外支出事件
 */
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
public class ExtraPaymentEvent implements Event {
    private ExtraPaymentEventType extraPaymentEventType;
    private BigDecimal payment;

    /**
     * 取得額外支出要付多少錢。
     *
     * @return 額外支出的金額
     */
    public BigDecimal getPayment() {
        return this.payment;
    }

    public ExtraPaymentEventType getExtraPaymentEventType() {
        return this.extraPaymentEventType;
    }

    @Override
    public EventType getEventType() {
        return EventType.EXTRA_PAYMENT;
    }

    @Override
    public void execute(Actor actor, Map<String, Object> param) throws InsufficientCashException {
        FinancialStatementV2 financialStatement = actor.getFinancialStatementV2();
        if (financialStatement.getCash().compareTo(getPayment()) < 0) {
            throw new InsufficientCashException();
        } else {
            financialStatement.subtractCash(getPayment());
        }
    }
}

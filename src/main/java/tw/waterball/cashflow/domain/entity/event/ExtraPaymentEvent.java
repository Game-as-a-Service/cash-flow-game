package tw.waterball.cashflow.domain.entity.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.ToString;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.FinancialStatement;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.math.BigDecimal;

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
        return EventType.ExtraPayment;
    }

    @Override
    public void execute(Actor actor) throws InsufficientCashException
    {
        FinancialStatement financialStatement = actor.getFinancialStatement();
        if (financialStatement.getCash().compareTo(getPayment()) == -1) {
            throw new InsufficientCashException();
        } else {
            financialStatement.subtractCash(getPayment());
        }
    }
}

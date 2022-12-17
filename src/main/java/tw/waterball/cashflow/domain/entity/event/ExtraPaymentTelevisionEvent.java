package tw.waterball.cashflow.domain.entity.event;

import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.FinancialStatement;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.math.BigDecimal;

public class ExtraPaymentTelevisionEvent implements Event {
    private static final BigDecimal payment = BigDecimal.valueOf(5000);

    @Override
    public EventType getEventType() {
        return EventType.ExtraPayment_Television;
    }

    @Override
    public void execute(Actor actor) throws InsufficientCashException {
        FinancialStatement financialStatement = actor.getFinancialStatement();
        if (financialStatement.getCash().compareTo(payment) == -1) {
            throw new InsufficientCashException();
        } else {
            financialStatement.subtractCash(payment);
        }
    }
}

package tw.waterball.cashflow.domain.entity.event;

import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.FinancialStatement;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.math.BigDecimal;

public class SettlementDateEvent implements Event {

    @Override
    public EventType getEventType() {
        return EventType.SettlementDate;
    }

    @Override
    public void execute(final Actor actor) throws InsufficientCashException {
        FinancialStatement financialStatement = actor.getFinancialStatement();
        financialStatement.addCash(financialStatement.getTotalIncomeAmount());
        financialStatement.subtractCash(financialStatement.getTotalExpenseAmount());

        if (financialStatement.getCash().compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientCashException();
        }
    }
}

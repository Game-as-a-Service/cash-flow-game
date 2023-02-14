package tw.waterball.cashflow.domain.entity.event;

import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.math.BigDecimal;
import java.util.Map;

public class SettlementDateEvent implements Event {

    @Override
    public EventType getEventType() {
        return EventType.SETTLEMENT_DATE;
    }

    @Override
    public void execute(final Actor actor, Map<String, Object> param) throws InsufficientCashException {
        FinancialStatementV2 financialStatement = actor.getFinancialStatementV2();
        financialStatement.addCash(financialStatement.getIncome().getTotalIncomeAmount());
        financialStatement.subtractCash(financialStatement.getExpense().getTotalExpenseAmount());
        if (financialStatement.getCash().compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientCashException();
        }
    }
}

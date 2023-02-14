package tw.waterball.cashflow.domain.entity.event;

import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.ActorState;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.math.BigDecimal;
import java.util.Map;

public class UnemploymentEvent implements Event {

    @Override
    public EventType getEventType() {
        return EventType.UNEMPLOYMENT;
    }

    @Override
    public void execute(Actor actor, Map<String, Object> param) throws InsufficientCashException {
        FinancialStatementV2 financialStmt = actor.getFinancialStatementV2();
        final BigDecimal payment = financialStmt.getExpense().getTotalExpenseAmount();

        if (financialStmt.getCash().compareTo(payment) < 0) {
            throw new InsufficientCashException();
        }

        financialStmt.subtractCash(payment); // 需付清所有 expenses

        actor.setState(ActorState.UNEMPLOYMENT);
        actor.setDiceCount(1);
        actor.setTurnNumber(2);
    }
}

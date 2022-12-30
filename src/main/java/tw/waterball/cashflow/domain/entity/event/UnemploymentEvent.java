package tw.waterball.cashflow.domain.entity.event;

import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.ActorState;
import tw.waterball.cashflow.domain.entity.FinancialStatement;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.math.BigDecimal;

public class UnemploymentEvent implements Event {

    @Override
    public EventType getEventType() {
        return EventType.UNEMPLOYMENT;
    }

    @Override
    public void execute(Actor actor) throws InsufficientCashException {
        FinancialStatement financialStmt = actor.getFinancialStatement();
        final BigDecimal payment = financialStmt.getTotalExpenseAmount();

        if(financialStmt.getCash().compareTo(payment) == -1)
        {
            throw new InsufficientCashException();
        }

        financialStmt.subtractCash(payment); // 需付清所有 expenses

        actor.setState(ActorState.UNEMPLOYMENT);
        actor.setDiceCount(1);
        actor.setTurnNumber(2);
    }
}

package tw.waterball.cashflow.domain.entity.event;

import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.FinancialStatement;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

public class BigOpportunityEvent implements Event {
    private BigOpportunityEventType bigOpportunityEventType;

    public BigOpportunityEvent(final BigOpportunityEventType bigOpportunityEventType) {
        this.bigOpportunityEventType = bigOpportunityEventType;
    }

    @Override
    public EventType getEventType() {
        return EventType.DEAL_OPPORTUNITY;
    }

    @Override
    public void execute(final Actor actor) throws InsufficientCashException {
        FinancialStatement financialStatement = actor.getFinancialStatement();
        if (financialStatement.getCash().compareTo(bigOpportunityEventType.getDownPayment()) < 0) {
            throw new InsufficientCashException("[Cash:DownPayment] [" + financialStatement.getCash() + ":" + bigOpportunityEventType.getDownPayment() + "]");
        }
    }
}

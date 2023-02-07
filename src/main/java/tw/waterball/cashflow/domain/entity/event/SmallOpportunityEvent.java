package tw.waterball.cashflow.domain.entity.event;

import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.FinancialItem;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

public class SmallOpportunityEvent implements Event {
    SmallOpportunityEventType smallOpportunityEventType;

    public SmallOpportunityEvent(SmallOpportunityEventType smallOpportunityEventType) {
        this.smallOpportunityEventType = smallOpportunityEventType;
    }

    @Override
    public EventType getEventType() {
        return EventType.DEAL_OPPORTUNITY;
    }

    @Override
    public void execute(Actor actor) throws InsufficientCashException {
        FinancialStatementV2 financialStatement = actor.getFinancialStatementV2();
        if (financialStatement.getCash().compareTo(smallOpportunityEventType.getDownPayment()) < 0) {
            throw new InsufficientCashException("[Cash:DownPayment] [" + financialStatement.getCash() + ":" + smallOpportunityEventType.getDownPayment() + "]");
        }
        financialStatement.subtractCash(smallOpportunityEventType.getDownPayment());
        String itemId = smallOpportunityEventType.name() + "-" + System.currentTimeMillis();

        financialStatement.getIncome().addRealEstate(FinancialItem.builder(itemId, smallOpportunityEventType.getName(), smallOpportunityEventType.getCashFlow()).build());
        financialStatement.getAsset().addRealEstate(FinancialItem.builder(itemId, smallOpportunityEventType.getName(), smallOpportunityEventType.getCost()).build());
        financialStatement.getLiability().addRealEstate(FinancialItem.builder(itemId, smallOpportunityEventType.getName(), smallOpportunityEventType.getCost().subtract(smallOpportunityEventType.getDownPayment())).build());
    }
}

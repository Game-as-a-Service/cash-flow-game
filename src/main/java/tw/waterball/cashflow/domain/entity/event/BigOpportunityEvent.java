package tw.waterball.cashflow.domain.entity.event;

import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.FinancialItem;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;
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
        FinancialStatementV2 financialStatement = actor.getFinancialStatementV2();
        if (financialStatement.getCash().compareTo(bigOpportunityEventType.getDownPayment()) < 0) {
            throw new InsufficientCashException("[Cash:DownPayment] [" + financialStatement.getCash() + ":" + bigOpportunityEventType.getDownPayment() + "]");
        }
        financialStatement.subtractCash(bigOpportunityEventType.getDownPayment());
        String itemId = bigOpportunityEventType.name() + "-" + System.currentTimeMillis();

        financialStatement.getIncome().addRealEstate(FinancialItem.builder(itemId, bigOpportunityEventType.getName(), bigOpportunityEventType.getCashFlow()).build());
        financialStatement.getAsset().addRealEstate(FinancialItem.builder(itemId, bigOpportunityEventType.getName(), bigOpportunityEventType.getCost()).build());
        financialStatement.getLiability().addRealEstate(FinancialItem.builder(itemId, bigOpportunityEventType.getName(), bigOpportunityEventType.getCost().subtract(bigOpportunityEventType.getDownPayment())).build());
    }
}

package tw.waterball.cashflow.domain.entity.event;

import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.FinancialItem;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

public class SmallRealEstateEvent implements Event {
    SmallRealEstateEventType smallRealEstateEventType;

    public SmallRealEstateEvent(SmallRealEstateEventType smallRealEstateEventType) {
        this.smallRealEstateEventType = smallRealEstateEventType;
    }

    @Override
    public EventType getEventType() {
        return EventType.DEAL_OPPORTUNITY;
    }

    @Override
    public void execute(Actor actor) throws InsufficientCashException {
        FinancialStatementV2 financialStatement = actor.getFinancialStatementV2();
        if (financialStatement.getCash().compareTo(smallRealEstateEventType.getDownPayment()) < 0) {
            throw new InsufficientCashException("[Cash:DownPayment] [" + financialStatement.getCash() + ":" + smallRealEstateEventType.getDownPayment() + "]");
        }
        financialStatement.subtractCash(smallRealEstateEventType.getDownPayment());
        String itemId = smallRealEstateEventType.name() + "-" + System.currentTimeMillis();

        financialStatement.getIncome().addRealEstate(FinancialItem.builder(itemId, smallRealEstateEventType.getName(), smallRealEstateEventType.getCashFlow()).build());
        financialStatement.getAsset().addRealEstate(FinancialItem.builder(itemId, smallRealEstateEventType.getName(), smallRealEstateEventType.getCost()).build());
        financialStatement.getLiability().addRealEstate(FinancialItem.builder(itemId, smallRealEstateEventType.getName(), smallRealEstateEventType.getCost().subtract(smallRealEstateEventType.getDownPayment())).build());
    }
}

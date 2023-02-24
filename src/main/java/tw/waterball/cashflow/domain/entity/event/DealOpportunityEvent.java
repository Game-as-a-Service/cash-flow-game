package tw.waterball.cashflow.domain.entity.event;

import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.util.Map;

public class DealOpportunityEvent implements Event {
    @Override
    public EventType getEventType() {
        return EventType.DEAL_OPPORTUNITY;
    }

    @Override
    public void execute(Actor actor, Map<String, Object> param) throws InsufficientCashException {
        // Do nothing
    }
}

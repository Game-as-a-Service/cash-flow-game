package tw.waterball.cashflow.domain.entity.event;

import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.ActorState;

public class CharityEvent implements Event {
    @Override
    public EventType getEventType() {
        return EventType.CHARITY;
    }

    public void execute(Actor actor) {
        actor.setTurnNumber(3);
        actor.setDiceCount(2);
        actor.setState(ActorState.CHARITY);
    }
}

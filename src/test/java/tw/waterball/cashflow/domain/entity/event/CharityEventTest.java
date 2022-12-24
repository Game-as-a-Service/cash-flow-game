package tw.waterball.cashflow.domain.entity.event;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import static tw.waterball.cashflow.domain.entity.Career.Engineer;

class CharityEventTest {
    CharityEvent charityEvent = new CharityEvent();

    @Test
    void giveActor_whenCharityEvent_thenTurnNumber3DiceCount2StateCharity() throws InsufficientCashException {
        // Given 玩家A
        Actor actor = new Actor("玩家A", Engineer);

        // When
        charityEvent.execute(actor);

        // Then
        Assertions.assertEquals(3, actor.getTurnNumber());
        Assertions.assertEquals(2, actor.getDiceCount());
        Assertions.assertEquals(EventType.CHARITY, actor.getState());
    }
}

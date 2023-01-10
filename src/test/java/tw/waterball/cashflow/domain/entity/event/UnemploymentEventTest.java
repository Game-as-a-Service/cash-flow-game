package tw.waterball.cashflow.domain.entity.event;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.ActorState;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.math.BigDecimal;

import static tw.waterball.cashflow.domain.entity.Career.ENGINEER;

class UnemploymentEventTest {
    UnemploymentEvent unemploymentEvent = new UnemploymentEvent();

    @Test
    void giveNotEnoughCash_whenUnemploymentEvent_thenThrowException() throws InsufficientCashException {  // Given 玩家A
        // Given
        Actor actor = new Actor("玩家A", ENGINEER);
        FinancialStatementV2 financialStatement = actor.getFinancialStatementV2();
        financialStatement.subtractCash(financialStatement.getCash());

        // When,Then
        Assertions.assertThrows(InsufficientCashException.class, () -> unemploymentEvent.execute(actor));
    }

    @Test
    void giveEnoughCash_whenUnemploymentEvent_thenPayEx() throws InsufficientCashException {  // Given 玩家A
        // Given
        Actor actor = new Actor("玩家A", ENGINEER);
        FinancialStatementV2 financialStatement = actor.getFinancialStatementV2();
        financialStatement.addCash(new BigDecimal(10000));
        BigDecimal originalCash = financialStatement.getCash();

        // When
        unemploymentEvent.execute(actor);

        // Then 破產
        BigDecimal finalCash = originalCash.subtract(financialStatement.getExpense().getTotalExpenseAmount());
        Assertions.assertEquals(finalCash, financialStatement.getCash());
        Assertions.assertEquals(1, actor.getDiceCount());
        Assertions.assertEquals(2, actor.getTurnNumber());
        Assertions.assertEquals(ActorState.UNEMPLOYMENT, actor.getState());
    }
}

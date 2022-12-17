package tw.waterball.cashflow.application.usecase;

import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.application.usecase.util.FinancialStatementUtils;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.domain.entity.event.Event;
import tw.waterball.cashflow.domain.entity.event.ExtraPaymentTelevisionEvent;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExtraPaymentUseCaseTest {
    @Test
    void give3MillionCash_whenDrawExtraPaymentTelevisionEvent_thenSubtractCase5000() throws InsufficientCashException {
        // Given
        Actor actor = new Actor("actor_a", Career.Engineer);
        actor.setFinancialStatement(FinancialStatementUtils.initialize(Career.Engineer));
        actor.getFinancialStatement().addCase(BigDecimal.valueOf(3000000));
        BigDecimal currentCash = actor.getFinancialStatement().getCash();

        // When
        Event extraPaymentTelevisionEvent = new ExtraPaymentTelevisionEvent();
        extraPaymentTelevisionEvent.execute(actor);

        // Then
        BigDecimal paidAmount = currentCash.subtract(actor.getFinancialStatement().getCash());
        assertThat(paidAmount).isEqualByComparingTo(BigDecimal.valueOf(5000));
    }
}

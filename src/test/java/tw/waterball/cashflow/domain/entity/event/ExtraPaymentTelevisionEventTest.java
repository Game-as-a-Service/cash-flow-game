package tw.waterball.cashflow.domain.entity.event;

import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.application.usecase.util.FinancialStatementUtils;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExtraPaymentTelevisionEventTest {
    @Test
    void give3MillionCash_whenDrawExtraPaymentTelevisionEvent_thenSubtractCase5000() throws InsufficientCashException {
        // Given 玩家有大於 5000 的儲蓄
        Actor actor = new Actor("actor_a", Career.Engineer);
        actor.setFinancialStatement(FinancialStatementUtils.initialize(Career.Engineer));
        actor.getFinancialStatement().addCase(BigDecimal.valueOf(3000000));
        BigDecimal currentCash = actor.getFinancialStatement().getCash();

        // When 假裝抽到購買電視額外支出
        Event extraPaymentTelevisionEvent = EventFactory.getExtraPaymentEvent(ExtraPaymentEventType.Television);
        extraPaymentTelevisionEvent.execute(actor);

        // Then 儲蓄少 5000
        BigDecimal paidAmount = currentCash.subtract(actor.getFinancialStatement().getCash());
        assertThat(paidAmount).isEqualByComparingTo(BigDecimal.valueOf(5000));
    }
}

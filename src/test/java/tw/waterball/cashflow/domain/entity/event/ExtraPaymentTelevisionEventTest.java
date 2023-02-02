package tw.waterball.cashflow.domain.entity.event;

import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ExtraPaymentTelevisionEventTest {
    @Test
    void give3MillionCash_whenDrawExtraPaymentTelevisionEvent_thenSubtractCase5000() throws InsufficientCashException {
        // Given 玩家有大於 5000 的儲蓄
        Actor actor = new Actor("actor_a", Career.ENGINEER);
        actor.getFinancialStatementV2().addCash(BigDecimal.valueOf(3000000));
        BigDecimal currentCash = actor.getFinancialStatementV2().getCash();

        // When 假裝抽到購買電視額外支出
        Event extraPaymentTelevisionEvent = EventFactory.getExtraPaymentEvent(ExtraPaymentEventType.TELEVISION);
        extraPaymentTelevisionEvent.execute(actor);

        // Then 儲蓄少 5000
        BigDecimal paidAmount = currentCash.subtract(actor.getFinancialStatementV2().getCash());
        assertThat(paidAmount).isEqualByComparingTo(ExtraPaymentEventType.TELEVISION.getPayment());
    }
}

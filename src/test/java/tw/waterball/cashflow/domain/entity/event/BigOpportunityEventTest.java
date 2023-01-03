package tw.waterball.cashflow.domain.entity.event;

import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.application.usecase.util.FinancialStatementUtils;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BigOpportunityEventTest {
    @Test
    void giveNegativeCashFlow_whenBigOpportunityEvent_thenSubtractCaseAndAddExpense() throws InsufficientCashException {
        // Given
        Actor actor = new Actor("actor_a", Career.Engineer);
        actor.getFinancialStatement().addCash(BigDecimal.valueOf(3000000));
        BigDecimal currentCash = actor.getFinancialStatement().getCash();

        // When 假裝抽到 BEDROOMS3_LIVING2_FOR_SALE
        Event bigOpportunityEvent = EventFactory.getBigOpportunityEvent(BigOpportunityEventType.BEDROOMS3_LIVING2_FOR_SALE);
        bigOpportunityEvent.execute(actor);

        // Then
        // 支付 20,000
        BigDecimal paidAmount = currentCash.subtract(actor.getFinancialStatement().getCash());
        assertThat(paidAmount).isEqualByComparingTo(BigDecimal.valueOf(20000));
//        儲蓄減少為2,980,000，
//        負債增加為 105,000，
//        手上擁有該房子，每月總支出增為 10,100。
//        進度條為 1,000,完成值10,100
    }
}

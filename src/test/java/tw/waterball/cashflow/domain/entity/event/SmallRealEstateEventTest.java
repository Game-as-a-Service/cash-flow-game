package tw.waterball.cashflow.domain.entity.event;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.math.BigDecimal;

class SmallRealEstateEventTest {

    @Test
    void giveHoseEventType_whenSmallRealEstateEvent_thenSubtractCaseAndAddIncome() throws InsufficientCashException {
        // Given
        Actor actor = new Actor("actor_a", Career.ENGINEER);
        FinancialStatementV2 financialStatement = actor.getFinancialStatementV2();
        financialStatement.addCash(BigDecimal.valueOf(3000000));
        BigDecimal originalCash = financialStatement.getCash();
        BigDecimal originalIncome = financialStatement.getIncome().getTotalIncomeAmount();
        BigDecimal originalAsset = financialStatement.getAsset().getTotalAssetAmount();
        BigDecimal originalLiability = financialStatement.getLiability().getTotalLiabilityAmount();

        // When 假裝抽到 BEDROOMS3_LIVING2_FOR_SALE
        Event SmallRealEstateEvent = EventFactory.getSmallRealEstateEvent(SmallRealEstateEventType.BEDROOMS3_LIVING2_FOR_SALE);
        SmallRealEstateEvent.execute(actor, null);

        // Then
        // 現金減少
        Assertions.assertEquals(SmallRealEstateEventType.BEDROOMS3_LIVING2_FOR_SALE.getDownPayment(), originalCash.subtract(financialStatement.getCash()));
        // 現金流增加
        Assertions.assertEquals(SmallRealEstateEventType.BEDROOMS3_LIVING2_FOR_SALE.getCashFlow(), financialStatement.getIncome().getTotalIncomeAmount().subtract(originalIncome));
        // 資產增加
        Assertions.assertEquals(SmallRealEstateEventType.BEDROOMS3_LIVING2_FOR_SALE.getCost(), financialStatement.getAsset().getTotalAssetAmount().subtract(originalAsset));
        // 負債增加
        Assertions.assertEquals(BigDecimal.valueOf(60000), financialStatement.getLiability().getTotalLiabilityAmount().subtract(originalLiability));
    }

    @Test
    void giveDownPaymentBiggerThenCash_whenSmallRealEstateEvent_thenThrowInsufficientCashException() {
        // Given
        Actor actor = new Actor("actor_a", Career.ENGINEER);
        BigDecimal currentCash = actor.getFinancialStatementV2().getCash();

        // 假裝抽到 BEDROOMS3_LIVING2_FOR_SALE
        Event smallRealEstateEvent = EventFactory.getSmallRealEstateEvent(SmallRealEstateEventType.BEDROOMS3_LIVING2_FOR_SALE);

        // When,Then
        Assertions.assertThrows(InsufficientCashException.class, () -> smallRealEstateEvent.execute(actor, null));
    }
}

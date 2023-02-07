package tw.waterball.cashflow.domain.entity.event;

import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.domain.entity.FinancialItem;
import tw.waterball.cashflow.domain.entity.FinancialItemName;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static tw.waterball.cashflow.domain.entity.event.MarketEventType.INFLATION_HIT;

class MarketEventTest {
    @Test
    void givenEngineerWithoutRealEstateAsset_whenInflationHitMarketEvent_thenNothingHappened() throws InsufficientCashException {
        // Given
        Actor engineer = new Actor("engineer", Career.ENGINEER);
        FinancialStatementV2 financialStmt = engineer.getFinancialStatementV2();
        BigDecimal oldIncomeAmount = financialStmt.getIncome().getTotalIncomeAmount();
        BigDecimal oldCashAmount = financialStmt.getCash();

        // When
        MarketEvent marketEvent = new MarketEvent(INFLATION_HIT, FinancialItemName.REAL_ESTATE_HOUSE_3_BR_2_BA, INFLATION_HIT.getAmount());
        marketEvent.execute(engineer);

        // Then
        assertThat(oldIncomeAmount).isEqualTo(financialStmt.getIncome().getTotalIncomeAmount());
        assertThat(oldCashAmount).isEqualTo(financialStmt.getCash());
    }

    @Test
    void givenEngineerWithRealEstateAsset_whenHouseBuyerMarketEventAndRealEstateIsSold_thenRealEstateIsRemoved() throws InsufficientCashException {
        // Given
        Actor engineer = new Actor("engineer", Career.ENGINEER);
        FinancialStatementV2 financialStmt = engineer.getFinancialStatementV2();
        final String itemID = String.valueOf(System.currentTimeMillis());
        financialStmt.getIncome().addRealEstate(FinancialItem.builder(itemID, FinancialItemName.REAL_ESTATE_HOUSE_3_BR_2_BA, BigDecimal.valueOf(-100)).build());
        financialStmt.getAsset().addRealEstate(FinancialItem.builder(itemID, FinancialItemName.REAL_ESTATE_HOUSE_3_BR_2_BA, BigDecimal.valueOf(50000)).build());
        financialStmt.getLiability().addRealEstate(FinancialItem.builder(itemID, FinancialItemName.REAL_ESTATE_HOUSE_3_BR_2_BA, BigDecimal.valueOf(50000)).build());
        BigDecimal oldIncomeAmount = financialStmt.getIncome().getTotalIncomeAmount();
        BigDecimal oldPassiveIncomeAmount = financialStmt.getIncome().getTotalPassiveIncomeAmount();
        BigDecimal oldCashAmount = financialStmt.getCash();

        // When
        MarketEvent marketEvent = new MarketEvent(INFLATION_HIT, FinancialItemName.REAL_ESTATE_HOUSE_3_BR_2_BA, INFLATION_HIT.getAmount());
        marketEvent.setRealEstateItemIDToSell(itemID);
        marketEvent.execute(engineer);

        // Then
        assertThat(financialStmt.getIncome().getTotalIncomeAmount().subtract(oldIncomeAmount)).isEqualTo(BigDecimal.valueOf(100));
        assertThat(financialStmt.getIncome().getTotalPassiveIncomeAmount().subtract(oldPassiveIncomeAmount)).isEqualTo(BigDecimal.valueOf(100));
        assertThat(financialStmt.getCash().subtract(oldCashAmount)).isEqualTo(BigDecimal.valueOf(15000));
        assertThat(financialStmt.getIncome().getRealEstate(itemID)).isEmpty();
        assertThat(financialStmt.getAsset().getRealEstate(itemID)).isEmpty();
        assertThat(financialStmt.getLiability().getRealEstate(itemID)).isEmpty();
    }
}

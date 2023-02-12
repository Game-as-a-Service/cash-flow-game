package tw.waterball.cashflow.domain.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {
    @Test
    void givenPlayerWithPassiveIncomeGreaterThanExpenses_whenIsInOuterCircle_thenReturnTrue() {
        // Given
        Actor actor = new Actor("Nick", Career.ENGINEER);
        FinancialStatementV2 fs = actor.getFinancialStatementV2();
        BigDecimal interestOfIncome = fs.getExpense().getTotalExpenseAmount().add(BigDecimal.ONE); // 讓被動收入大於總支出
        fs.getIncome().addInterest(FinancialItem.builder("item_cd", FinancialItemName.CD, interestOfIncome).build());

        // When
        boolean isReadyToOuterCircle = actor.isInOuterCircle();

        // Then
        assertTrue(isReadyToOuterCircle);
    }

    @Test
    void givenPlayerWithPassiveIncomeLessThanExpenses_whenIsInOuterCircle_thenReturnFalse() {
        // Given
        Actor actor = new Actor("Nick", Career.ENGINEER);
        FinancialStatementV2 fs = actor.getFinancialStatementV2();
        BigDecimal interestOfIncome = fs.getExpense().getTotalExpenseAmount().subtract(BigDecimal.ONE); // 讓被動收入小於總支出
        fs.getIncome().addInterest(FinancialItem.builder("item_cd", FinancialItemName.CD, interestOfIncome).build());

        // When
        boolean isReadyToOuterCircle = actor.isInOuterCircle(); // 初始財務的被動收入會小於總支出

        // Then
        assertFalse(isReadyToOuterCircle);
    }

    @Test
    void givenPlayerWithNoPassiveIncome_whenIsInOuterCircle_thenReturnFalse() {
        // Given
        Actor actor = new Actor("Nick", Career.ENGINEER);

        // When
        boolean isReadyToOuterCircle = actor.isInOuterCircle(); // 初始財務的被動收入會小於總支出

        // Then
        assertEquals(BigDecimal.ZERO,actor.getFinancialStatementV2().getIncome().getTotalPassiveIncomeAmount());
        assertFalse(isReadyToOuterCircle);
    }

    @Test
    void givenPlayerWithEqualPassiveIncomeAndExpenses_whenIsInOuterCircle_thenReturnTrue() {
        // Given
        Actor actor = new Actor("Nick", Career.ENGINEER);
        FinancialStatementV2 fs = actor.getFinancialStatementV2();
        BigDecimal interestOfIncome = fs.getExpense().getTotalExpenseAmount(); // 讓被動收入等於總支出
        fs.getIncome().addInterest(FinancialItem.builder("item_cd", FinancialItemName.CD, interestOfIncome).build());

        // When
        boolean isReadyToOuterCircle = actor.isInOuterCircle(); // 初始財務的被動收入會小於總支出

        // Then
        assertTrue(isReadyToOuterCircle);
    }
}
package tw.waterball.cashflow.domain.entity;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.expense.Expense;
import tw.waterball.cashflow.domain.entity.expense.ExpenseType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ActorTest {
    @Test
    void givenPlayerWithPassiveIncomeGreaterThanExpenses_whenIsInOuterCircle_thenReturnsTrue() {
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
    void givenPlayerWithPassiveIncomeLessThanExpenses_whenIsInOuterCircle_thenReturnsFalse() {
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
    void givenPlayerWithNoPassiveIncome_whenIsInOuterCircle_thenReturnsFalse() {
        // Given
        Actor actor = new Actor("Nick", Career.ENGINEER);

        // When
        boolean isReadyToOuterCircle = actor.isInOuterCircle(); // 初始財務的被動收入會小於總支出

        // Then
        assertTrue(actor.getFinancialStatementV2().getIncome().getTotalPassiveIncomeAmount().equals(BigDecimal.ZERO));
        assertFalse(isReadyToOuterCircle);
    }

    @Test
    @Disabled
    void givenPlayerWithNoExpenses_whenIsInOuterCircle_thenReturnsTrue() {
        Actor actor = new Actor("Nick", Career.ENGINEER);
        FinancialStatement financialStatement = new FinancialStatement();
        Map<ExpenseType, Expense> expenseMap = new HashMap<>();
        expenseMap.put(ExpenseType.TAX,
                       Expense.builder(ExpenseType.TAX).amount(BigDecimal.valueOf(0)).build());
        financialStatement.setPassiveIncome(new BigDecimal(200));
        financialStatement.setExpenseMap(expenseMap);
        actor.setFinancialStatement(financialStatement);
        assertTrue(actor.isInOuterCircle());
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
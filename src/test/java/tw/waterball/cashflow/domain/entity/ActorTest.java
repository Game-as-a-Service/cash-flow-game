package tw.waterball.cashflow.domain.entity;

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
        Actor actor = new Actor("Nick", Career.ENGINEER);
        FinancialStatement financialStatement = new FinancialStatement();
        Map<ExpenseType, Expense> expenseMap = new HashMap<>();
        expenseMap.put(ExpenseType.TAX,
                       Expense.builder(ExpenseType.TAX).amount(BigDecimal.valueOf(80)).build());
        financialStatement.setPassiveIncome(new BigDecimal(200));
        financialStatement.setExpenseMap(expenseMap);
        actor.setFinancialStatement(financialStatement);
        assertTrue(actor.isInOuterCircle());
    }

    @Test
    void givenPlayerWithPassiveIncomeLessThanExpenses_whenIsInOuterCircle_thenReturnsFalse() {
        Actor actor = new Actor("Nick", Career.ENGINEER);
        FinancialStatement financialStatement = new FinancialStatement();
        Map<ExpenseType, Expense> expenseMap = new HashMap<>();
        expenseMap.put(ExpenseType.TAX,
                       Expense.builder(ExpenseType.TAX).amount(BigDecimal.valueOf(200)).build());
        financialStatement.setPassiveIncome(new BigDecimal(100));
        financialStatement.setExpenseMap(expenseMap);
        actor.setFinancialStatement(financialStatement);
        assertFalse(actor.isInOuterCircle());
    }

    @Test
    void givenPlayerWithNoPassiveIncome_whenIsInOuterCircle_thenReturnsFalse() {
        Actor actor = new Actor("Nick", Career.ENGINEER);
        FinancialStatement financialStatement = new FinancialStatement();
        Map<ExpenseType, Expense> expenseMap = new HashMap<>();
        expenseMap.put(ExpenseType.TAX,
                       Expense.builder(ExpenseType.TAX).amount(BigDecimal.valueOf(200)).build());
        financialStatement.setPassiveIncome(new BigDecimal(0));
        financialStatement.setExpenseMap(expenseMap);
        actor.setFinancialStatement(financialStatement);
        assertFalse(actor.isInOuterCircle());
    }

    @Test
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
    void givenPlayerWithEqualPassiveIncomeAndExpenses_whenIsInOuterCircle_thenReturnsFalse() {
        Actor actor = new Actor("Nick", Career.ENGINEER);
        FinancialStatement financialStatement = new FinancialStatement();
        Map<ExpenseType, Expense> expenseMap = new HashMap<>();
        expenseMap.put(ExpenseType.TAX,
                       Expense.builder(ExpenseType.TAX).amount(BigDecimal.valueOf(200)).build());
        financialStatement.setPassiveIncome(new BigDecimal(200));
        financialStatement.setExpenseMap(expenseMap);
        actor.setFinancialStatement(financialStatement);
        assertTrue(actor.isInOuterCircle());
    }
}
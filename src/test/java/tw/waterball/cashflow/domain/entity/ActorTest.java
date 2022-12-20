package tw.waterball.cashflow.domain.entity;

import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.expense.Expense;
import tw.waterball.cashflow.domain.entity.expense.ExpenseType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {
    @Test
    public void givenPlayerWithPassiveIncomeGreaterThanExpenses_whenIsInOuterCircle_thenReturnsTrue() {
        Actor actor = new Actor("Nick", Career.Engineer);
        FinancialStatement financialStatement = new FinancialStatement();
        Map<ExpenseType, Expense> expenseMap = new HashMap<>();
        expenseMap.put(ExpenseType.Tax,
                       Expense.builder(ExpenseType.Tax).amount(BigDecimal.valueOf(80)).build());
        financialStatement.setPassiveIncome(new BigDecimal(200));
        financialStatement.setExpenseMap(expenseMap);
        actor.setFinancialStatement(financialStatement);
        assertTrue(actor.isInOuterCircle());
    }

    @Test
    public void givenPlayerWithPassiveIncomeLessThanExpenses_whenIsInOuterCircle_thenReturnsFalse() {
        Actor actor = new Actor("Nick", Career.Engineer);
        FinancialStatement financialStatement = new FinancialStatement();
        Map<ExpenseType, Expense> expenseMap = new HashMap<>();
        expenseMap.put(ExpenseType.Tax,
                       Expense.builder(ExpenseType.Tax).amount(BigDecimal.valueOf(200)).build());
        financialStatement.setPassiveIncome(new BigDecimal(100));
        financialStatement.setExpenseMap(expenseMap);
        actor.setFinancialStatement(financialStatement);
        assertFalse(actor.isInOuterCircle());
    }

    @Test
    public void givenPlayerWithNoPassiveIncome_whenIsInOuterCircle_thenReturnsFalse() {
        Actor actor = new Actor("Nick", Career.Engineer);
        FinancialStatement financialStatement = new FinancialStatement();
        Map<ExpenseType, Expense> expenseMap = new HashMap<>();
        expenseMap.put(ExpenseType.Tax,
                       Expense.builder(ExpenseType.Tax).amount(BigDecimal.valueOf(200)).build());
        financialStatement.setPassiveIncome(new BigDecimal(0));
        financialStatement.setExpenseMap(expenseMap);
        actor.setFinancialStatement(financialStatement);
        assertFalse(actor.isInOuterCircle());
    }

    @Test
    public void givenPlayerWithNoExpenses_whenIsInOuterCircle_thenReturnsTrue() {
        Actor actor = new Actor("Nick", Career.Engineer);
        FinancialStatement financialStatement = new FinancialStatement();
        Map<ExpenseType, Expense> expenseMap = new HashMap<>();
        expenseMap.put(ExpenseType.Tax,
                       Expense.builder(ExpenseType.Tax).amount(BigDecimal.valueOf(0)).build());
        financialStatement.setPassiveIncome(new BigDecimal(200));
        financialStatement.setExpenseMap(expenseMap);
        actor.setFinancialStatement(financialStatement);
        assertTrue(actor.isInOuterCircle());
    }

    @Test
    public void givenPlayerWithEqualPassiveIncomeAndExpenses_whenIsInOuterCircle_thenReturnsFalse() {
        Actor actor = new Actor("Nick", Career.Engineer);
        FinancialStatement financialStatement = new FinancialStatement();
        Map<ExpenseType, Expense> expenseMap = new HashMap<>();
        expenseMap.put(ExpenseType.Tax,
                       Expense.builder(ExpenseType.Tax).amount(BigDecimal.valueOf(200)).build());
        financialStatement.setPassiveIncome(new BigDecimal(200));
        financialStatement.setExpenseMap(expenseMap);
        actor.setFinancialStatement(financialStatement);
        assertTrue(actor.isInOuterCircle());
    }
}
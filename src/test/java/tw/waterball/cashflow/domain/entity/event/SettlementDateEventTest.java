package tw.waterball.cashflow.domain.entity.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.FinancialStatement;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;
import tw.waterball.cashflow.domain.entity.expense.Expense;
import tw.waterball.cashflow.domain.entity.expense.ExpenseType;

import java.math.BigDecimal;

import static tw.waterball.cashflow.domain.entity.Career.Engineer;

class SettlementDateEventTest {

    SettlementDateEvent settlementDateEvent = new SettlementDateEvent();

    @Test
    void giveIncomeMoreThenExpense_whenDrawSettlementDateEvent_thenIncreaseCash() throws InsufficientCashException {
        // Given 玩家A
        Actor actor = new Actor("玩家A", Engineer);
        FinancialStatement financialStatement = actor.getFinancialStatement();
        BigDecimal totalIncomeAmount = financialStatement.getTotalIncomeAmount();
        BigDecimal totalExpenseAmount = financialStatement.getTotalExpenseAmount();
        BigDecimal cash = financialStatement.getCash();

        // When 玩家A擲骰子，並走到銀行結算日格子
        settlementDateEvent.execute(actor);

        // Then 領取
         BigDecimal finalCash = cash.add(totalIncomeAmount).subtract(totalExpenseAmount);
        Assertions.assertEquals(finalCash, actor.getFinancialStatement().getCash());
    }

    @Test
    void giveIncomeLessThenExpense_whenDrawSettlementDateEvent_thenDecreaseCash() throws InsufficientCashException {
        // Given 玩家A
        Actor actor = new Actor("玩家A", Engineer);
        FinancialStatement financialStatement = actor.getFinancialStatement();
        financialStatement.addExpense(Expense.builder(ExpenseType.INTEREST).amount(BigDecimal.valueOf(920)).build());
        BigDecimal totalIncomeAmount = financialStatement.getTotalIncomeAmount();
        BigDecimal totalExpenseAmount = financialStatement.getTotalExpenseAmount();
        BigDecimal cash = financialStatement.getCash();

        // When 玩家A擲骰子，並走到銀行結算日格子
        settlementDateEvent.execute(actor);

        // Then 支付
        BigDecimal finalCash = cash.add(totalIncomeAmount).subtract(totalExpenseAmount);
        Assertions.assertEquals(finalCash, actor.getFinancialStatement().getCash());
    }


    @Test
    void giveExpenseMoreThenIncomePlusCash_whenDrawSettlementDateEvent_thenBankruptcy() {
        // Given 玩家A
        Actor actor = new Actor("玩家A", Engineer);
        FinancialStatement financialStatement = actor.getFinancialStatement();
        financialStatement.addExpense(Expense.builder(ExpenseType.Interest).amount(
                financialStatement.getCash().add(financialStatement.getTotalIncomeAmount())
        ).build());

        // When 玩家A擲骰子，並走到銀行結算日格子 Then 破產
        Assertions.assertThrows(InsufficientCashException.class, () -> settlementDateEvent.execute(actor));
    }
}
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
        // Given 玩家A，總收入2500，支出1590，儲蓄 200
        Actor actor = new Actor("玩家A", Engineer);
        FinancialStatement financialStatement = actor.getFinancialStatement();
        BigDecimal cash = BigDecimal.valueOf(200);
        financialStatement.addCash(cash);

        // When 玩家A擲骰子，並走到銀行結算日格子
        settlementDateEvent.execute(actor);

        // Then 領取 910，儲蓄1110
        BigDecimal totalIncomeAmount = financialStatement.getTotalIncomeAmount();
        BigDecimal totalExpenseAmount = financialStatement.getTotalExpenseAmount();
        BigDecimal finalCash = cash.add(totalIncomeAmount).subtract(totalExpenseAmount);
        Assertions.assertEquals(finalCash, actor.getFinancialStatement().getCash());
    }

    @Test
    void giveIncomeLessThenExpense_whenDrawSettlementDateEvent_thenDecreaseCash() throws InsufficientCashException {
        // Given 玩家A，總收入2500，支出1590，儲蓄 200
        Actor actor = new Actor("玩家A", Engineer);
        FinancialStatement financialStatement = actor.getFinancialStatement();
        financialStatement.addExpense(Expense.builder(ExpenseType.INTEREST).amount(BigDecimal.valueOf(920)).build());
        BigDecimal cash = BigDecimal.valueOf(200);
        financialStatement.addCash(cash);

        // When 玩家A擲骰子，並走到銀行結算日格子
        settlementDateEvent.execute(actor);

        // Then 支付 910，儲蓄190
        BigDecimal totalIncomeAmount = financialStatement.getTotalIncomeAmount();
        BigDecimal totalExpenseAmount = financialStatement.getTotalExpenseAmount();
        BigDecimal finalCash = cash.add(totalIncomeAmount).subtract(totalExpenseAmount);
        Assertions.assertEquals(finalCash, actor.getFinancialStatement().getCash());
    }


    @Test
    void giveExpenseMoreThenIncomePlusCash_whenDrawSettlementDateEvent_thenBankruptcy() {
        // Given 玩家A，總收入2500，支出1590，儲蓄 200
        Actor actor = new Actor("玩家A", Engineer);
        FinancialStatement financialStatement = actor.getFinancialStatement();
        financialStatement.addExpense(Expense.builder(ExpenseType.INTEREST).amount(BigDecimal.valueOf(920)).build());

        // When 玩家A擲骰子，並走到銀行結算日格子 Then 破產
        Assertions.assertThrows(InsufficientCashException.class, () -> settlementDateEvent.execute(actor));
    }
}
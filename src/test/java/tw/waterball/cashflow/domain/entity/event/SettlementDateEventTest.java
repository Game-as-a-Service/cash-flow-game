package tw.waterball.cashflow.domain.entity.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.FinancialItem;
import tw.waterball.cashflow.domain.entity.FinancialItemName;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.math.BigDecimal;

import static tw.waterball.cashflow.domain.entity.Career.ENGINEER;

class SettlementDateEventTest {

    SettlementDateEvent settlementDateEvent = new SettlementDateEvent();

    @Test
    void giveIncomeMoreThenExpense_whenDrawSettlementDateEvent_thenIncreaseCash() throws InsufficientCashException {
        // Given 玩家A
        Actor actor = new Actor("玩家A", ENGINEER);
        FinancialStatementV2 financialStatement = actor.getFinancialStatementV2();
        BigDecimal totalIncomeAmount = financialStatement.getIncome().getTotalIncomeAmount();
        BigDecimal totalExpenseAmount = financialStatement.getExpense().getTotalExpenseAmount();
        BigDecimal cash = financialStatement.getCash();

        // When 玩家A擲骰子，並走到銀行結算日格子
        settlementDateEvent.execute(actor, null);

        // Then 領取
        BigDecimal finalCash = cash.add(totalIncomeAmount).subtract(totalExpenseAmount);
        Assertions.assertEquals(finalCash, actor.getFinancialStatementV2().getCash());
    }

    @Test
    void giveIncomeLessThenExpense_whenDrawSettlementDateEvent_thenDecreaseCash() throws InsufficientCashException {
        // Given 玩家A
        Actor actor = new Actor("玩家A", ENGINEER);
        FinancialStatementV2 financialStatement = actor.getFinancialStatementV2();
        financialStatement.getExpense().addExpense(FinancialItem.builder(String.valueOf(System.currentTimeMillis()), FinancialItemName.CREDIT_CARD, BigDecimal.valueOf(920)).build());
        BigDecimal totalIncomeAmount = financialStatement.getIncome().getTotalIncomeAmount();
        BigDecimal totalExpenseAmount = financialStatement.getExpense().getTotalExpenseAmount();
        BigDecimal cash = financialStatement.getCash();

        // When 玩家A擲骰子，並走到銀行結算日格子
        settlementDateEvent.execute(actor, null);

        // Then 支付
        BigDecimal finalCash = cash.add(totalIncomeAmount).subtract(totalExpenseAmount);
        Assertions.assertEquals(finalCash, actor.getFinancialStatementV2().getCash());
    }


    @Test
    void giveExpenseMoreThenIncomePlusCash_whenDrawSettlementDateEvent_thenBankruptcy() {
        // Given 玩家A
        Actor actor = new Actor("玩家A", ENGINEER);
        FinancialStatementV2 financialStatement = actor.getFinancialStatementV2();
        financialStatement.getExpense().addExpense(
                FinancialItem.builder(String.valueOf(System.currentTimeMillis()), FinancialItemName.CREDIT_CARD, financialStatement.getCash().add(
                        financialStatement.getIncome().getTotalIncomeAmount())).build());

        // When 玩家A擲骰子，並走到銀行結算日格子 Then 破產
        Assertions.assertThrows(InsufficientCashException.class, () -> settlementDateEvent.execute(actor, null));
    }
}
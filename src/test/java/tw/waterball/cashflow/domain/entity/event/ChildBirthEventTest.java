package tw.waterball.cashflow.domain.entity.event;

import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.domain.entity.FinancialItem;
import tw.waterball.cashflow.domain.entity.FinancialItemName;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ChildBirthEventTest {
    /**
     * Give:<br>
     * 一個沒有孩子的工程師<br>
     * When:<br>
     * 抽到生小孩事件<br>
     * Then:<br>
     * 增加一個小孩的支出項目
     */
    @Test
    void givenEngineerWithoutChild_whenChildBirthEvent_thenAddOneChildExpense() throws InsufficientCashException {
        // Given
        Actor engineer = new Actor("engineer", Career.ENGINEER);
        BigDecimal oneChildExpense = engineer.getCareer().getChildExpense(); // 記住工程師的小孩支出
        BigDecimal oldTotalExpenseAmount = engineer.getFinancialStatementV2().getExpense().getTotalExpenseAmount(); // 目前未有小孩的總支出

        // When
        ChildBirthEvent event = new ChildBirthEvent();
        event.execute(engineer, null); // 抽中生小孩事件

        // Then
        Optional<FinancialItem> childExpense = engineer.getFinancialStatementV2().getExpense().getExpense(FinancialItemName.CHILD.name());
        assertThat(childExpense).isNotEmpty();
        assertThat(childExpense.get().getCount()).isEqualTo(1);
        BigDecimal newTotalExpenseAmount = engineer.getFinancialStatementV2().getExpense().getTotalExpenseAmount();
        assertThat(newTotalExpenseAmount.subtract(oldTotalExpenseAmount)).isEqualTo(oneChildExpense);
    }

    /**
     * Give:<br>
     * 已有一個孩子的工程師<br>
     * When:<br>
     * 抽到生小孩事件<br>
     * Then:<br>
     * 再增加一個小孩的支出項目，孩子總數為 2。
     */
    @Test
    void givenEngineerWithOneChild_whenChildBirthEvent_thenTotalTwoChildExpenses() throws InsufficientCashException {
        // Given
        Actor engineer = new Actor("engineer", Career.ENGINEER);
        ChildBirthEvent event = new ChildBirthEvent();
        event.execute(engineer, null); // 製造已有一個小孩的情況
        BigDecimal oneChildExpense = engineer.getCareer().getChildExpense(); // 記住工程師的小孩支出
        FinancialStatementV2 financialStmt = engineer.getFinancialStatementV2();
        BigDecimal oldTotalExpenseAmount = financialStmt.getExpense().getTotalExpenseAmount(); // 目前已有一個小孩的總支出
        BigDecimal oldTotalPassiveIncomeAmount = financialStmt.getIncome().getTotalPassiveIncomeAmount(); // 目前被動收入

        // When
        event.execute(engineer, null); // 抽中生小孩事件

        // Then
        Optional<FinancialItem> childExpense = financialStmt.getExpense().getExpense(FinancialItemName.CHILD.name());
        assertThat(childExpense).isNotEmpty();
        assertThat(childExpense.get().getCount()).isEqualTo(2);
        BigDecimal newTotalExpenseAmount = financialStmt.getExpense().getTotalExpenseAmount();
        assertThat(newTotalExpenseAmount.subtract(oldTotalExpenseAmount)).isEqualTo(oneChildExpense); // 又增加一個小孩的支出
        assertThat(oldTotalPassiveIncomeAmount).isEqualTo(financialStmt.getIncome().getTotalPassiveIncomeAmount()); // 被動收入不變
    }

    /**
     * Give:<br>
     * 已有 3 個孩子的工程師<br>
     * When:<br>
     * 抽到生小孩事件<br>
     * Then:<br>
     * 不會發生任何事情。
     */
    @Test
    void givenEngineerWithThreeChild_whenChildBirthEvent_thenNothingHappened() throws InsufficientCashException {
        // Given
        Actor engineer = new Actor("engineer", Career.ENGINEER);
        ChildBirthEvent event = new ChildBirthEvent();
        event.execute(engineer, null);
        FinancialStatementV2 financialStmt = engineer.getFinancialStatementV2();
        FinancialItem childExpense = financialStmt.getExpense().getExpense(FinancialItemName.CHILD.name()).get();
        final int maxThreeChildCount = 3;
        childExpense.setCount(maxThreeChildCount); // 製造已有 3 個小孩的情況
        BigDecimal oldTotalExpenseAmount = financialStmt.getExpense().getTotalExpenseAmount(); // 目前已有 3 個小孩的總支出
        BigDecimal oldTotalPassiveIncomeAmount = financialStmt.getIncome().getTotalPassiveIncomeAmount(); // 目前被動收入

        // When
        event.execute(engineer, null); // 抽中生小孩事件

        // Then
        assertThat(childExpense.getCount()).isEqualTo(maxThreeChildCount); // 小孩總數不變
        assertThat(oldTotalExpenseAmount).isEqualTo(financialStmt.getExpense().getTotalExpenseAmount()); // 總支出不變
        assertThat(oldTotalPassiveIncomeAmount).isEqualTo(financialStmt.getIncome().getTotalPassiveIncomeAmount()); // 被動收入不變
    }
}

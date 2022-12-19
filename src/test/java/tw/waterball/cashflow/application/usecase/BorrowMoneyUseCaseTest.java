package tw.waterball.cashflow.application.usecase;

import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.domain.entity.expense.Expense;
import tw.waterball.cashflow.domain.entity.expense.ExpenseType;
import tw.waterball.cashflow.domain.entity.liability.Liability;
import tw.waterball.cashflow.domain.entity.liability.LiabilityType;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BorrowMoneyUseCaseTest {

    @Test
    void giveActor_whenBorrowMoney_thenSuccess() {
        BorrowMoneyUseCase borrowMoneyUseCase = new BorrowMoneyUseCase();
        // Given
        Actor actor = new Actor("A", Career.Engineer);

        // When
        actor = borrowMoneyUseCase.borrowMoney(actor, BigDecimal.valueOf(9000));

        // Then
        Optional<Liability> liabilityOptional = actor.getFinancialStatement().getLiability(LiabilityType.CashLoan);
        assertThat(liabilityOptional).isPresent();
        assertThat(liabilityOptional.get().getAmount()).isEqualTo(BigDecimal.valueOf(9000));

        Optional<Expense> expenseOptional = actor.getFinancialStatement().getExpense(ExpenseType.CashLoanPayment);
        assertThat(expenseOptional).isPresent();
        assertThat(expenseOptional.get().getAmount()).isEqualTo(BigDecimal.valueOf(900));
    }
}
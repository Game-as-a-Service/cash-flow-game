package tw.waterball.cashflow.application.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.domain.entity.expense.Expense;
import tw.waterball.cashflow.domain.entity.expense.ExpenseType;
import tw.waterball.cashflow.domain.entity.liability.Liability;
import tw.waterball.cashflow.domain.entity.liability.LiabilityType;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class RepayMoneyUseCaseTest {
    @Test
    void giveActor_whenRepayMoneyIsCashLoan_thenSuccess() {
        RepayMoneyUseCase repayMoneyUseCase = new RepayMoneyUseCase();
        // Given
        Actor actor = new Actor("A", Career.Engineer);
        actor.getFinancialStatement().addLiability(Liability.builder(LiabilityType.CashLoan).amount(BigDecimal.valueOf(10000)).build());
        actor.getFinancialStatement().addExpense(Expense.builder(ExpenseType.CashLoanPayment).amount(BigDecimal.valueOf(1000)).build());

        // When
        actor = repayMoneyUseCase.repayMoney(actor, "CashLoan", BigDecimal.valueOf(5000));


        // Then
        assertThat(actor.getFinancialStatement().getLiability(LiabilityType.CashLoan).orElseThrow(() -> new RuntimeException("CashLoan is not exist")).getAmount()).isEqualTo(BigDecimal.valueOf(5000));
        assertThat(actor.getFinancialStatement().getExpense(ExpenseType.CashLoanPayment).orElseThrow(() -> new RuntimeException("CashLoanPayment is not exist")).getAmount()).isEqualTo(BigDecimal.valueOf(500));
        assertThat(actor.getFinancialStatement().getCash()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    void giveActor_whenRepayMoneyIsNotCashLoan_thenSuccess() {
        RepayMoneyUseCase repayMoneyUseCase = new RepayMoneyUseCase();
        // Given
        Actor actor = new Actor("A", Career.Engineer);

        // When
        actor = repayMoneyUseCase.repayMoney(actor, "CarLoans", BigDecimal.valueOf(4000));

        // Then
        assertThat(actor.getFinancialStatement().getLiability(LiabilityType.CarLoans).isPresent()).isFalse();
        assertThat(actor.getFinancialStatement().getExpense(ExpenseType.CarLoanPayment).isPresent()).isFalse();
        assertThat(actor.getFinancialStatement().getCash()).isEqualTo(BigDecimal.valueOf(1000));
    }

    @Test
    void giveActor_whenRepayMoneyIsCashLoan_thenActorDoesntHaveEnoughMoney() {
        RepayMoneyUseCase repayMoneyUseCase = new RepayMoneyUseCase();
        // Given
        Actor actor = new Actor("A", Career.Engineer);

        // When & Then
        Assertions.assertThrows(RuntimeException.class, () -> repayMoneyUseCase.repayMoney(actor, "HomeMortgage", BigDecimal.valueOf(38000)));
    }
}

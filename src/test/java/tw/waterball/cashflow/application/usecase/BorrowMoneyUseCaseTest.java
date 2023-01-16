package tw.waterball.cashflow.application.usecase;

import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.domain.entity.FinancialItem;
import tw.waterball.cashflow.domain.entity.FinancialItemName;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BorrowMoneyUseCaseTest {

    @Test
    void giveActor_whenBorrowMoney_thenSuccess() {
        BorrowMoneyUseCase borrowMoneyUseCase = new BorrowMoneyUseCase();
        // Given
        Actor actor = new Actor("A", Career.ENGINEER);

        // When
        actor = borrowMoneyUseCase.borrowMoney(actor, BigDecimal.valueOf(9000));

        // Then
        Optional<FinancialItem> liabilityOptional = actor.getFinancialStatementV2().getLiability().getAllBasicLiabilities().stream()
                                                         .filter(liability -> liability.getName() == FinancialItemName.LOAN).findFirst();
        assertThat(liabilityOptional).isPresent();
        assertThat(liabilityOptional.get().getAmount()).isEqualTo(BigDecimal.valueOf(9000));


        Optional<FinancialItem> expenseOptional = actor.getFinancialStatementV2().getExpense().getExpense(liabilityOptional.get().getId());
        assertThat(expenseOptional).isPresent();
        assertThat(expenseOptional.get().getAmount()).isEqualTo(BigDecimal.valueOf(900));
    }
}
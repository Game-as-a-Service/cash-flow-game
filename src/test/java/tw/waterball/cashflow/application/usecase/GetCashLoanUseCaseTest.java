package tw.waterball.cashflow.application.usecase;

import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.Career;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class GetCashLoanUseCaseTest {

    @Test
    void giveActor_whenGetCashLoan_thenSuccess() {
        tw.waterball.cashflow.usecase.GetCashLoanUseCase getBankLoan = new tw.waterball.cashflow.usecase.GetCashLoanUseCase();
        // Given
        Actor engineer = new Actor("A", Career.Engineer);

        // When
        Boolean result = getBankLoan.getCashLoan(engineer.getFinancialStatement(), BigDecimal.valueOf(9000));

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void giveActor_whenGetCashLoan_thenFail() {
        tw.waterball.cashflow.usecase.GetCashLoanUseCase getBankLoan = new tw.waterball.cashflow.usecase.GetCashLoanUseCase();
        // Given
        Actor engineer = new Actor("A", Career.Engineer);

        // When
        Boolean result = getBankLoan.getCashLoan(engineer.getFinancialStatement(), BigDecimal.valueOf(20000));

        // Then
        assertThat(result).isFalse();
    }
}
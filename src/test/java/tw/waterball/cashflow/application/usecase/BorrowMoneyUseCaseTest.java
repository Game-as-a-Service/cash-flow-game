package tw.waterball.cashflow.application.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tw.waterball.cashflow.application.repository.ActorRepository;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BorrowMoneyUseCaseTest {
    @InjectMocks
    private BorrowMoneyUseCase useCase;

    @Mock
    private ActorRepository actorRepo;

    /**
     * <pre>
     * Given
     * 玩家A,儲蓄 400，目前負債 87,000，總支出 3,150，
     * When
     * 玩家A進行回合，玩家A選擇借 1,000,000
     * Then
     * 銀行借錢給玩家A，玩家A儲蓄增加為 1,000,400，
     * 負債增加為 1,087,000，
     * 每月總支出增為 103,150。(貸款支出為貸款金額之10%)
     * </pre>
     */
    @Test
    void givenEngineer_whenBorrowOneMillion_thenCashAddsOneMillionAndLiabilityAddsOneMillionAndExpenseAddsOneHundredThousand() {
        // Given
        Actor engineer = new Actor("engineer", Career.ENGINEER);
        FinancialStatementV2 financialStmt = engineer.getFinancialStatementV2();
        BigDecimal oldCash = financialStmt.getCash();
        BigDecimal oldTotalExpense = financialStmt.getExpense().getTotalExpenseAmount();
        BigDecimal oldTotalLiability = financialStmt.getLiability().getTotalLiabilityAmount();

        // When
        when(actorRepo.findByActorId(anyString())).thenReturn(Optional.of(engineer));
        BigDecimal oneMillion = BigDecimal.valueOf(100_0000);
        useCase.borrowMoney(engineer.getActorId(), oneMillion);

        // Then
        assertThat(financialStmt.getCash().subtract(oldCash)).isEqualTo(oneMillion);
        assertThat(financialStmt.getLiability().getTotalLiabilityAmount().subtract(oldTotalLiability)).isEqualTo(oneMillion);
        assertThat(financialStmt.getExpense().getTotalExpenseAmount().subtract(oldTotalExpense)).isEqualTo(oneMillion.multiply(BigDecimal.valueOf(0.1)));
    }
}

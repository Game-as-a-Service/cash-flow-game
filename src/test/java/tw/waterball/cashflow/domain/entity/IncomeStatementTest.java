package tw.waterball.cashflow.domain.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class IncomeStatementTest {
    @Test
    void testTotalIncomeAmount()
    {
        IncomeStatement incomeStatement = new IncomeStatement(FinancialItem.builder(String.valueOf(System.currentTimeMillis()),
                                                                                    FinancialItemName.SALARY,
                                                                                    BigDecimal.valueOf(300)).build());
        incomeStatement.addInterest(FinancialItem.builder(String.valueOf(System.currentTimeMillis()),
                                                          FinancialItemName.CD,
                                                          BigDecimal.valueOf(20)).build());
        incomeStatement.addInterest(FinancialItem.builder(String.valueOf(System.currentTimeMillis() + 1),
                                                          FinancialItemName.CD,
                                                          BigDecimal.valueOf(40)).build());

        Assertions.assertThat(incomeStatement.getTotalPassiveIncomeAmount()).isEqualTo(BigDecimal.valueOf(60));
        Assertions.assertThat(incomeStatement.getTotalIncomeAmount()).isEqualTo(BigDecimal.valueOf(360));
    }
}

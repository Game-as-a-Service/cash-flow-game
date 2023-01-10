package tw.waterball.cashflow.domain.entity.income;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @deprecated
 *
 * @see tw.waterball.cashflow.domain.entity.IncomeStatement
 * @see tw.waterball.cashflow.domain.entity.FinancialItem
 * @see tw.waterball.cashflow.domain.entity.FinancialItemName
 * @see tw.waterball.cashflow.domain.entity.FinancialStatementV2
 */
@Getter
@Builder
@ToString
public class Income {
    private IncomeType type;
    private BigDecimal amount;

    public static IncomeBuilder builder(IncomeType type) {
        return new IncomeBuilder().type(type);
    }
}

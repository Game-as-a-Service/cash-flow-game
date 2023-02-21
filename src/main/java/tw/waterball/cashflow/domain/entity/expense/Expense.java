package tw.waterball.cashflow.domain.entity.expense;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @deprecated
 *
 * @see tw.waterball.cashflow.domain.entity.ExpenseStatement
 * @see tw.waterball.cashflow.domain.entity.FinancialItem
 * @see tw.waterball.cashflow.domain.entity.FinancialItemName
 * @see tw.waterball.cashflow.domain.entity.FinancialStatementV2
 */
@Getter
@ToString
@Builder
@Deprecated(forRemoval = true)
public class Expense {
    private ExpenseType type;
    private BigDecimal amount;

    public static ExpenseBuilder builder(ExpenseType type) {
        return new ExpenseBuilder().type(type);
    }
}

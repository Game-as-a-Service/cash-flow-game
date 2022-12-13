package tw.waterball.cashflow.domain.entity.expense;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@Builder
public class Expense {
    private ExpenseType type;
    private BigDecimal amount;

    public static ExpenseBuilder builder(ExpenseType type)
    {
        return new ExpenseBuilder().type(type);
    }
}

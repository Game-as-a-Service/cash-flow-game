package tw.waterball.cashflow.domain.entity.expense;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@Builder
public class Expense {
    private ExpenseType type;
    private long amount;

    public static ExpenseBuilder builder(ExpenseType type)
    {
        return new ExpenseBuilder().type(type);
    }
}

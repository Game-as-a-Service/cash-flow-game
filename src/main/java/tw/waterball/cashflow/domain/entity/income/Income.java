package tw.waterball.cashflow.domain.entity.income;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@ToString
public class Income {
    private IncomeType type;
    private long amount;

    public static IncomeBuilder builder(IncomeType type)
    {
        return new IncomeBuilder().type(type);
    }
}

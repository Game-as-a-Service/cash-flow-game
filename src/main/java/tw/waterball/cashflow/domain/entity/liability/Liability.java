package tw.waterball.cashflow.domain.entity.liability;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @deprecated
 *
 * @see tw.waterball.cashflow.domain.entity.LiabilityStatement
 * @see tw.waterball.cashflow.domain.entity.FinancialItem
 * @see tw.waterball.cashflow.domain.entity.FinancialItemName
 * @see tw.waterball.cashflow.domain.entity.FinancialStatementV2
 */
@Builder
@Getter
@ToString
public class Liability {
    private LiabilityType type;
    private BigDecimal amount;

    public static LiabilityBuilder builder(LiabilityType type)
    {
        return new LiabilityBuilder().type(type);
    }
}

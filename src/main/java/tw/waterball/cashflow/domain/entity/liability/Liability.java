package tw.waterball.cashflow.domain.entity.liability;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

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

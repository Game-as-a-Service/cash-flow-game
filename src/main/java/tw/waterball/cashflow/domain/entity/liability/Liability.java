package tw.waterball.cashflow.domain.entity.liability;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Liability {
    private LiabilityType type;
    private long amount;

    public static LiabilityBuilder builder(LiabilityType type)
    {
        return new LiabilityBuilder().type(type);
    }
}

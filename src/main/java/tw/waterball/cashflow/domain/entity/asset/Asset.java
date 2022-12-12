package tw.waterball.cashflow.domain.entity.asset;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@Getter
@ToString
public class Asset {
    private AssetType type;
    private BigDecimal amount;

    public static AssetBuilder builder(AssetType type)
    {
        return new AssetBuilder().type(type);
    }
}

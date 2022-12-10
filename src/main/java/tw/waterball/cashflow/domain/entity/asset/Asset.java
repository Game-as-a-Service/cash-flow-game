package tw.waterball.cashflow.domain.entity.asset;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Asset {
    private AssetType type;
    private long amount;

    public static AssetBuilder builder(AssetType type)
    {
        return new AssetBuilder().type(type);
    }
}

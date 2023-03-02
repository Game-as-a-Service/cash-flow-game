package tw.waterball.cashflow.domain.entity.asset;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;


/**
 * @deprecated
 *
 * @see tw.waterball.cashflow.domain.entity.AssetStatement
 * @see tw.waterball.cashflow.domain.entity.FinancialItem
 * @see tw.waterball.cashflow.domain.entity.FinancialItemName
 * @see tw.waterball.cashflow.domain.entity.FinancialStatementV2
 */
@Builder
@Getter
@ToString
@Deprecated(forRemoval = true)
public class Asset {
    private AssetType type;
    private BigDecimal amount;

    public static AssetBuilder builder(AssetType type)
    {
        return new AssetBuilder().type(type);
    }
}

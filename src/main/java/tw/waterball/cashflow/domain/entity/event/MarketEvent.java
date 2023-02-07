package tw.waterball.cashflow.domain.entity.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.FinancialItem;
import tw.waterball.cashflow.domain.entity.FinancialItemName;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.math.BigDecimal;

/**
 * 市場風雲事件
 */
@RequiredArgsConstructor
@Getter
@ToString
public class MarketEvent implements Event {
    /**
     * 市場風雲的種類
     */
    private final MarketEventType marketEventType;

    /**
     * 資產名稱
     */
    private final FinancialItemName realEstateItemName;

    /**
     * 此事件的金額
     */
    private final BigDecimal amount;

    @Setter
    private String realEstateItemIDToSell;

    @Override
    public EventType getEventType() {
        return EventType.MARKET;
    }

    @Override
    public void execute(Actor actor) throws InsufficientCashException {
        FinancialStatementV2 financialStmt = actor.getFinancialStatementV2();
        if(!financialStmt.getAsset().realEstateExists(this.realEstateItemName)) {
            return;
        }

        FinancialItem realEstateItem = financialStmt.getAsset().getRealEstate(this.realEstateItemIDToSell).orElseThrow();
        financialStmt.addCash(this.amount.subtract(realEstateItem.getTotalAmount())); // 銀行買價 - 資產金額 = 銀行實際付款金額
        financialStmt.getIncome().removeIncome(this.realEstateItemIDToSell); // 收入移除該資產產生的現金流
        financialStmt.getAsset().removeAsset(this.realEstateItemIDToSell); // 資產移除該資產
        financialStmt.getLiability().removeLiability(this.realEstateItemIDToSell); // 負債移除該資產
    }
}

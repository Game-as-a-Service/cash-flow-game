package tw.waterball.cashflow.domain.entity.event;

import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.FinancialItem;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;
import tw.waterball.cashflow.domain.entity.exception.InsufficientSharesException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class StockEvent implements Event {
    public static final String PARAM_IS_BUY = "isBuy";
    public static final String PARAM_SHARES = "shares";
    public static final String PARAM_ITEM_ID = "itemId";
    StockEventType stockEventType;

    public StockEvent(StockEventType stockEventType) {
        this.stockEventType = stockEventType;
    }

    @Override
    public EventType getEventType() {
        return EventType.DEAL_OPPORTUNITY;
    }

    @Override
    public void execute(Actor actor, Map<String, Object> param) throws InsufficientCashException {
        boolean isBuy = Boolean.TRUE.equals(param.get(PARAM_IS_BUY));
        int shares = Integer.parseInt(param.get(PARAM_SHARES).toString());
        FinancialStatementV2 financialStatement = actor.getFinancialStatementV2();
        if (isBuy) {
            // buy
            BigDecimal totalCost = stockEventType.getCost().multiply(BigDecimal.valueOf(shares));

            if (financialStatement.getCash().compareTo(totalCost) < 0) {
                throw new InsufficientCashException("[Cash:Cost:Shares] [" + financialStatement.getCash() + ":" + stockEventType.getCost() + ":" + shares + "]");
            }
            financialStatement.subtractCash(totalCost);
            String itemId = stockEventType.name() + "-" + System.currentTimeMillis();

            if (stockEventType.getCashFlow().compareTo(BigDecimal.ZERO) > 0) {
                financialStatement.getIncome().addDividend(FinancialItem.builder(itemId, stockEventType.getName(), stockEventType.getCashFlow()).count(shares).build());
            }
            financialStatement.getAsset().addStock(FinancialItem.builder(itemId, stockEventType.getName(), stockEventType.getCost()).count(shares).build());
        } else {
            // sell
            String itemId = (String) param.get(PARAM_ITEM_ID);
            Optional<FinancialItem> dividendOptional = financialStatement.getIncome().getDividend(itemId);
            dividendOptional.ifPresent(dividendItem -> {
                if (dividendItem.getCount() < shares) {
                    // 股數不夠
                    throw new InsufficientSharesException("[Shares:Have] [" + shares + ":" + dividendItem.getCount() + "]");
                } else if (dividendItem.getCount() == shares) {
                    financialStatement.getIncome().removeIncome(itemId);
                } else {
                    dividendItem.setCount(dividendItem.getCount() - shares);
                }
            });
            BigDecimal totalCost = stockEventType.getCost().multiply(BigDecimal.valueOf(shares));
            financialStatement.addCash(totalCost);

            financialStatement.getAsset().removeAsset(itemId);
        }
    }

}

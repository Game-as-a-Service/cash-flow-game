package tw.waterball.cashflow.domain.entity.event;

import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.util.Map;

/**
 * Title: StockEvent<br>
 * Description:<br>
 * Company: Tradevan Co.<br>
 *
 * @author 2920
 * @version 修訂記錄:<br>
 * @since 2023/2/9
 */
public class StockEvent implements Event{
    StockEventType stockEventType;
    boolean sell = false;

    public StockEvent(StockEventType stockEventType){
        this.stockEventType = stockEventType;
    }

    @Override
    public EventType getEventType() {
        return EventType.DEAL_OPPORTUNITY;
    }

    public void setAction(boolean sell){
        this.sell = sell;
    }

    @Override
    public void execute(Actor actor, Map<String, Object> param) throws InsufficientCashException {
        if(sell){
            // sell
        }else{
            // buy
        }
    }


}

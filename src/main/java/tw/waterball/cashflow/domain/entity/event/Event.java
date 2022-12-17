package tw.waterball.cashflow.domain.entity.event;

import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

public interface Event {
    /**
     * @return 事件類型
     * @see EventType
     */
    EventType getEventType();

    /**
     * 此事件發生的事情
     * @param actor 玩家
     * @throws InsufficientCashException 儲蓄不足
     */
    void execute(Actor actor) throws InsufficientCashException;
}

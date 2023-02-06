package tw.waterball.cashflow.domain.entity.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

/**
 * 作為取得事件物件的單一入口
 * 構想:
 * 事件物件應該是 singleton，不需要 new 多個 instance 來使用。
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventFactory {
    private static Map<ExtraPaymentEventType, ExtraPaymentEvent> extraPaymentEventMap = new EnumMap<>(ExtraPaymentEventType.class);
    private static Random random = new Random();

    static
    {
        for(ExtraPaymentEventType extraPaymentEventType : ExtraPaymentEventType.values())
        {
            extraPaymentEventMap.put(extraPaymentEventType, new ExtraPaymentEvent(extraPaymentEventType, extraPaymentEventType.getAmount()));
        }
    }

    /**
     * 根據 event type 取得 event 物件
     * @param eventType 主要 event type
     * @param subEventType 次要 event type
     * @return event object
     */
    public static Event getEvent(EventType eventType, Optional<Object> subEventType)
    {
        // TODO 待實作
        throw new UnsupportedOperationException();
    }

    public static ExtraPaymentEvent getExtraPaymentEvent(ExtraPaymentEventType extraPaymentEventType)
    {
        return extraPaymentEventMap.get(extraPaymentEventType);
    }

    public static ExtraPaymentEvent randomExtraPayment()
    {
        return extraPaymentEventMap.get(ExtraPaymentEventType.values()[random.nextInt(extraPaymentEventMap.size())]);
    }
}

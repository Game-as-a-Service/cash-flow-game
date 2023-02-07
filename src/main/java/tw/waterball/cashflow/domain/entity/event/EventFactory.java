package tw.waterball.cashflow.domain.entity.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 作為取得事件物件的單一入口
 * 構想:
 * 事件物件應該是 singleton，不需要 new 多個 instance 來使用。
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventFactory {
    private static Map<ExtraPaymentEventType, ExtraPaymentEvent> extraPaymentEventMap = new HashMap<>();
    private static Map<BigOpportunityEventType, BigOpportunityEvent> bigOpportunityEventMap = new HashMap<>();
    private static Map<SmallOpportunityEventType, SmallOpportunityEvent> smallOpportunityEventMap = new HashMap<>();
    private static Random random = new Random();

    static {
        for (ExtraPaymentEventType extraPaymentEventType : ExtraPaymentEventType.values()) {
            extraPaymentEventMap.put(extraPaymentEventType, new ExtraPaymentEvent(extraPaymentEventType, extraPaymentEventType.getPayment()));
        }
        for (BigOpportunityEventType bigOpportunityEventType : BigOpportunityEventType.values()) {
            bigOpportunityEventMap.put(bigOpportunityEventType, new BigOpportunityEvent(bigOpportunityEventType));
        }

        for (SmallOpportunityEventType smallOpportunityEventType : SmallOpportunityEventType.values()) {
            smallOpportunityEventMap.put(smallOpportunityEventType, new SmallOpportunityEvent(smallOpportunityEventType));
        }
    }

    public static Event getEvent(EventType eventType) {
        // TODO 待實作
        throw new UnsupportedOperationException();
    }

    public static ExtraPaymentEvent getExtraPaymentEvent(ExtraPaymentEventType extraPaymentEventType) {
        return extraPaymentEventMap.get(extraPaymentEventType);
    }

    public static ExtraPaymentEvent randomExtraPayment() {
        return extraPaymentEventMap.get(ExtraPaymentEventType.values()[random.nextInt(extraPaymentEventMap.size())]);
    }

    public static BigOpportunityEvent getBigOpportunityEvent(BigOpportunityEventType bigOpportunityEventType) {
        return bigOpportunityEventMap.get(bigOpportunityEventType);
    }

    public static Event getSmallOpportunityEvent(SmallOpportunityEventType smallOpportunityEventType) {
        return smallOpportunityEventMap.get(smallOpportunityEventType);
    }
}

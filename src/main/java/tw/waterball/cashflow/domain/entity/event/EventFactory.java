package tw.waterball.cashflow.domain.entity.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tw.waterball.cashflow.domain.entity.FinancialItemName;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
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
    private static Map<BigOpportunityEventType, BigOpportunityEvent> bigOpportunityEventMap = new HashMap<>();
    private static List<FinancialItemName> realEstateList = Arrays.asList(FinancialItemName.REAL_ESTATE_CONDO_2_BR_1_BA, FinancialItemName.REAL_ESTATE_HOUSE_3_BR_2_BA);
    private static Random random = new Random();

    static {
        for (ExtraPaymentEventType extraPaymentEventType : ExtraPaymentEventType.values()) {
            extraPaymentEventMap.put(extraPaymentEventType, new ExtraPaymentEvent(extraPaymentEventType, extraPaymentEventType.getAmount()));
        }
        for (BigOpportunityEventType bigOpportunityEventType : BigOpportunityEventType.values()) {
            bigOpportunityEventMap.put(bigOpportunityEventType, new BigOpportunityEvent(bigOpportunityEventType));
        }
    }

    /**
     * 根據 event type 取得 event 物件
     *
     * @param eventType    主要 event type
     * @param subEventType 次要 event type
     * @return event object
     */
    public static Event getEvent(EventType eventType, Optional<Object> subEventType) {
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

    public static MarketEvent randomMarketEvent() {
        MarketEventType randomMarketEventType = MarketEventType.values()[random.nextInt(MarketEventType.values().length)];
        FinancialItemName randomRealEstate = realEstateList.get(random.nextInt(realEstateList.size()));
        return new MarketEvent(randomMarketEventType, randomRealEstate, randomMarketEventType.getAmount());
    }
}

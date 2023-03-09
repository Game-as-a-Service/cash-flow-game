package tw.waterball.cashflow.domain.entity.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tw.waterball.cashflow.domain.entity.FinancialItemName;

import java.util.Arrays;
import java.util.EnumMap;
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
    private static Map<BigOpportunityEventType, BigOpportunityEvent> bigOpportunityEventMap = new EnumMap<>(BigOpportunityEventType.class);
    private static Map<SmallRealEstateEventType, SmallRealEstateEvent> smallRealEstateEventMap = new EnumMap<>(SmallRealEstateEventType.class);
    private static Map<StockEventType, StockEvent> storckEventMap = new EnumMap<>(StockEventType.class);
    private static List<FinancialItemName> realEstateList = Arrays.asList(FinancialItemName.REAL_ESTATE_CONDO_2_BR_1_BA, FinancialItemName.REAL_ESTATE_HOUSE_3_BR_2_BA);
    private static CharityEvent charityEvent = new CharityEvent();
    private static SettlementDateEvent settlementDateEvent = new SettlementDateEvent();
    private static UnemploymentEvent unemploymentEvent = new UnemploymentEvent();
    private static DealOpportunityEvent dealOpportunityEvent = new DealOpportunityEvent();
    private static ChildBirthEvent childBirthEvent = new ChildBirthEvent();
    private static Random random = new Random();

    static {
        for (ExtraPaymentEventType extraPaymentEventType : ExtraPaymentEventType.values()) {
            extraPaymentEventMap.put(extraPaymentEventType, new ExtraPaymentEvent(extraPaymentEventType, extraPaymentEventType.getAmount()));
        }
        for (BigOpportunityEventType bigOpportunityEventType : BigOpportunityEventType.values()) {
            bigOpportunityEventMap.put(bigOpportunityEventType, new BigOpportunityEvent(bigOpportunityEventType));
        }
        for (SmallRealEstateEventType smallRealEstateEventType : SmallRealEstateEventType.values()) {
            smallRealEstateEventMap.put(smallRealEstateEventType, new SmallRealEstateEvent(smallRealEstateEventType));
        }
        for (StockEventType stockEventType : StockEventType.values()) {
            storckEventMap.put(stockEventType, new StockEvent(stockEventType));
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

    public static Event getEvent(EventType eventType) {
        switch(eventType) {
            case MARKET:
                return randomMarketEvent();
            case EXTRA_PAYMENT:
                return randomExtraPayment();
            case DEAL_OPPORTUNITY:
                return dealOpportunityEvent;
            case SETTLEMENT_DATE:
                return settlementDateEvent;
            case CHARITY:
                return charityEvent;
            case UNEMPLOYMENT:
                return unemploymentEvent;
            case CHILD:
                return childBirthEvent;
            default:
                throw new UnsupportedOperationException();
        }
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

    public static BigOpportunityEvent randomBigOpportunity() {
        return bigOpportunityEventMap.get(BigOpportunityEventType.values()[random.nextInt(bigOpportunityEventMap.size())]);
    }

    public static Event getSmallRealEstateEvent(SmallRealEstateEventType smallRealEstateEventType) {
        return smallRealEstateEventMap.get(smallRealEstateEventType);
    }

    public static Event getStockEvent(StockEventType stockEventType){
        return storckEventMap.get(stockEventType);
    }

    public static Event randomSmallOpportunity() {
        if (random.nextInt(2) == 0) {
            return smallRealEstateEventMap.get(SmallRealEstateEventType.values()[random.nextInt(smallRealEstateEventMap.size())]);
        } else {
            return storckEventMap.get(StockEventType.values()[random.nextInt(storckEventMap.size())]);
        }
    }

    public static MarketEvent randomMarketEvent() {
        MarketEventType randomMarketEventType = MarketEventType.values()[random.nextInt(MarketEventType.values().length)];
        FinancialItemName randomRealEstate = realEstateList.get(random.nextInt(realEstateList.size()));
        return new MarketEvent(randomMarketEventType, randomRealEstate, randomMarketEventType.getAmount());
    }
}

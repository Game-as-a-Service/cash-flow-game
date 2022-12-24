package tw.waterball.cashflow.domain.entity.event;

/**
 * 主要事件的種類
 *
 * @see ExtraPaymentEventType
 */
public enum EventType {
    /**
     * 慈善事件
     */
    CHARITY,
    /**
     * 銀行結算日
     */
    SETTLEMENT_DATE,
    /**
     * 小孩 / 小孩出生 / 小孩教育
     */
    CHILD,
    /**
     * 額外支出
     */
    EXTRA_PAYMENT,
    /**
     * 失業
     */
    UNEMPLOYMENT,
    /**
     * 交易機會
     */
    DEAL_OPPORTUNITY
}

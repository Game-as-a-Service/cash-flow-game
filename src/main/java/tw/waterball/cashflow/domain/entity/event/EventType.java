package tw.waterball.cashflow.domain.entity.event;

public enum EventType {
    /**
     * 慈善事件
     */
    Charity,
    /**
     * 銀行結算日
     */
    SettlementDate,
    /**
     * 小孩 / 小孩出生 / 小孩教育
     */
    Child,
    /**
     * 額外支出
     */
    ExtraPayment
}

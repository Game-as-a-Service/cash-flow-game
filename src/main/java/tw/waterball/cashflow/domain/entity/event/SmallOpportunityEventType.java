package tw.waterball.cashflow.domain.entity.event;

import lombok.Getter;
import tw.waterball.cashflow.domain.entity.FinancialItemName;

import java.math.BigDecimal;


public enum SmallOpportunityEventType {
    BEDROOMS3_LIVING2_FOR_SALE(FinancialItemName.BEDROOMS3_LIVING2_FOR_SALE, 65000, 5000, 160);

    /**
     * 名稱
     */
    @Getter
    private final FinancialItemName name;

    /**
     * 成本
     */
    @Getter
    private final BigDecimal cost;
    /**
     * 首期支付
     */
    @Getter
    private final BigDecimal downPayment;
    /**
     * 月現金流
     */
    @Getter
    private final BigDecimal cashFlow;

    SmallOpportunityEventType(FinancialItemName name, long cost, long downPayment, long cashFlow) {
        this.name = name;
        this.cost = BigDecimal.valueOf(cost);
        this.downPayment = BigDecimal.valueOf(downPayment);
        this.cashFlow = BigDecimal.valueOf(cashFlow);
    }

}

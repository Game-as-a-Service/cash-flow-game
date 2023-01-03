package tw.waterball.cashflow.domain.entity.event;

import lombok.Getter;

import java.math.BigDecimal;

public enum BigOpportunityEventType {
    BEDROOMS3_LIVING2_FOR_SALE(125000, 105000, 20000, -100);

    /**
     * 成本
     */
    @Getter
    private final BigDecimal cost;
    /**
     * 抵押貸款
     */
    @Getter
    private final BigDecimal mortgage;
    /**
     * 首期支付
     */
    @Getter
    private final BigDecimal downPayment;
    /**
     * 月現金流
     */
    @Getter
    private final BigDecimal monthlyCashFlow;

    BigOpportunityEventType(long cost, long mortgage, long downPayment, long monthlyCashFlow) {
        this.cost = BigDecimal.valueOf(cost);
        this.mortgage = BigDecimal.valueOf(mortgage);
        this.downPayment = BigDecimal.valueOf(downPayment);
        this.monthlyCashFlow = BigDecimal.valueOf(monthlyCashFlow);
    }

}

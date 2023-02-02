package tw.waterball.cashflow.domain.entity.event;

import lombok.Getter;
import tw.waterball.cashflow.domain.entity.FinancialItemName;

import java.math.BigDecimal;


public enum BigOpportunityEventType {
    BEDROOMS3_LIVING2_FOR_SALE(FinancialItemName.BEDROOMS3_LIVING2_FOR_SALE, 125000, 105000, 20000, -100);

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

    BigOpportunityEventType(FinancialItemName name, long cost, long mortgage, long downPayment, long monthlyCashFlow) {
        this.name = name;
        this.cost = BigDecimal.valueOf(cost);
        this.mortgage = BigDecimal.valueOf(mortgage);
        this.downPayment = BigDecimal.valueOf(downPayment);
        this.monthlyCashFlow = BigDecimal.valueOf(monthlyCashFlow);
    }

}

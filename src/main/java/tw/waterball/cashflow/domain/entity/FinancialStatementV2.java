package tw.waterball.cashflow.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
@ToString
public class FinancialStatementV2 {
    /**
     * 儲蓄金額
     */
    private BigDecimal cash = BigDecimal.ZERO;
    private final IncomeStatement income;
    private final ExpenseStatement expense;
    private final AssetStatement asset;
    private final LiabilityStatement liability;

    /**
     * 增加儲蓄金額
     *
     * @param amount 要增加的金額，eg: 5000
     */
    public void addCash(BigDecimal amount) {
        this.cash = this.cash.add(amount);
    }

    /**
     * 減少儲蓄金額
     *
     * @param amount 要減少的金額，eg: 100
     */
    public void subtractCash(BigDecimal amount) {
        this.cash = this.cash.subtract(amount);
    }

    /**
     * @return 結薪日金額
     */
    public BigDecimal getPayday() {
        return income.getTotalIncomeAmount().subtract(expense.getTotalExpenseAmount());
    }
}

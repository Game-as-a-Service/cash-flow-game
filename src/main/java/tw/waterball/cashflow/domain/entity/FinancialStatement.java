package tw.waterball.cashflow.domain.entity;

import lombok.NoArgsConstructor;
import lombok.ToString;
import tw.waterball.cashflow.domain.entity.asset.Asset;
import tw.waterball.cashflow.domain.entity.asset.AssetType;
import tw.waterball.cashflow.domain.entity.expense.Expense;
import tw.waterball.cashflow.domain.entity.expense.ExpenseType;
import tw.waterball.cashflow.domain.entity.income.Income;
import tw.waterball.cashflow.domain.entity.income.IncomeType;
import tw.waterball.cashflow.domain.entity.liability.Liability;
import tw.waterball.cashflow.domain.entity.liability.LiabilityType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @deprecated
 *
 * @see FinancialStatementV2
 */
@NoArgsConstructor
@ToString
@Deprecated
public class FinancialStatement {
    private BigDecimal passiveIncome;
    private BigDecimal cash = BigDecimal.ZERO;

    private Map<IncomeType, Income> incomeMap = new HashMap<>();
    private Map<ExpenseType, Expense> expenseMap = new HashMap<>();
    private Map<AssetType, Asset> assetMap = new HashMap<>();
    private Map<LiabilityType, Liability> liabilityMap = new HashMap<>();

    public BigDecimal getTotalIncomeAmount() {
        return incomeMap.values().stream().map(Income::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalExpenseAmount() {
        return expenseMap.values().stream().map(Expense::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalAssetAmount() {
        return assetMap.values().stream().map(Asset::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalLiabilityAmount() {
        return liabilityMap.values().stream().map(Liability::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getPayday() {
        return getTotalIncomeAmount().subtract(getTotalExpenseAmount());
    }

    public void addIncome(final Income income) {
        if (!incomeMap.containsKey(income.getType())) {
            incomeMap.put(income.getType(), income);
        }
    }

    /**
     * 取得指定種類的收入
     *
     * @param incomeType 收入種類
     * @return 指定的收入物件
     */
    public Optional<Income> getIncome(final IncomeType incomeType) {
        return Optional.ofNullable(this.incomeMap.get(incomeType));
    }

    public void addExpense(final Expense expense) {
        if (!expenseMap.containsKey(expense.getType())) {
            expenseMap.put(expense.getType(), expense);
        }
    }

    public void addLiability(final Liability liability) {
        if (!liabilityMap.containsKey(liability.getType())) {
            liabilityMap.put(liability.getType(), liability);
        }
    }

    /**
     * @return 目前的儲蓄金額
     */
    public BigDecimal getCash() {
        return this.cash;
    }

    /**
     * 增加儲蓄金額
     *
     * @param amount 增加的數值，eg: 5000
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

    public BigDecimal getPassiveIncome() {
        return passiveIncome;
    }

    public void setPassiveIncome(final BigDecimal passiveIncome) {
        this.passiveIncome = passiveIncome;
    }

    public Map<ExpenseType, Expense> getExpenseMap() {
        return expenseMap;
    }

    /**
     * @see #addExpense(Expense)
     * @deprecated 不建議直接設定內部的 map
     */
    @Deprecated
    public void setExpenseMap(final Map<ExpenseType, Expense> expenseMap) {
        this.expenseMap = expenseMap;
    }
}

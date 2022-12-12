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

@NoArgsConstructor
@ToString
public class FinancialStatement {
    private BigDecimal passiveIncome;
    private BigDecimal cash;

    private Map<IncomeType, Income> incomeMap = new HashMap<>();
    private Map<ExpenseType, Expense> expenseMap = new HashMap<>();
    private Map<AssetType, Asset> assetMap = new HashMap<>();
    private Map<LiabilityType, Liability> liabilityMap = new HashMap<>();

    public BigDecimal getTotalIncomeAmount()
    {
        return incomeMap.values().stream().map(income -> income.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalExpenseAmount()
    {
        return expenseMap.values().stream().map(expense -> expense.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalAssetAmount()
    {
        return assetMap.values().stream().map(asset -> asset.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalLiabilityAmount()
    {
        return liabilityMap.values().stream().map(liability -> liability.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getPayday()
    {
        return getTotalIncomeAmount().subtract(getTotalExpenseAmount());
    }

    public void addIncome(Income income)
    {
        if(!incomeMap.containsKey(income.getType()))
        {
            incomeMap.put(income.getType(), income);
        }
    }

    /**
     * 取得指定種類的收入
     * @param incomeType 收入種類
     * @return 指定的收入物件
     */
    public Optional<Income> getIncome(IncomeType incomeType)
    {
        return Optional.ofNullable(this.incomeMap.get(incomeType));
    }

    public void addExpense(Expense expense)
    {
        if(!expenseMap.containsKey(expense.getType()))
        {
            expenseMap.put(expense.getType(), expense);
        }
    }

    public void addLiability(Liability liability) {
        if(!liabilityMap.containsKey(liability.getType()))
        {
            liabilityMap.put(liability.getType(), liability);
        }
    }
}
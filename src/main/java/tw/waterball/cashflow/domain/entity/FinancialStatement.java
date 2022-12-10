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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor
@ToString
public class FinancialStatement {
//    private long totalIncome;
//    private long totalExpenses;
    private long passiveIncome;
    private long cash;

    private Map<IncomeType, Income> incomeMap = new HashMap<>();
    private Map<ExpenseType, Expense> expenseMap = new HashMap<>();
    private Map<AssetType, Asset> assetMap = new HashMap<>();
    private Map<LiabilityType, Liability> liabilityMap = new HashMap<>();

    public long getTotalIncomeAmount()
    {
        return incomeMap.values().stream().mapToLong(Income::getAmount).sum();
    }

    public long getTotalExpenseAmount()
    {
        return expenseMap.values().stream().mapToLong(Expense::getAmount).sum();
    }

    public long getTotalAssetAmount()
    {
        return assetMap.values().stream().mapToLong(Asset::getAmount).sum();
    }

    public long getTotalLiabilityAmount()
    {
        return liabilityMap.values().stream().mapToLong(Liability::getAmount).sum();
    }

    public long getPayday()
    {
        return getTotalIncomeAmount() - getTotalExpenseAmount();
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

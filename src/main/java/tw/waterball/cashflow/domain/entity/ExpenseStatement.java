package tw.waterball.cashflow.domain.entity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ExpenseStatement {
    private Map<String, FinancialItem> map = new HashMap<>();

    public void addExpense(FinancialItem expenseItem) {
        this.map.put(expenseItem.getId(), expenseItem);
    }

    public Optional<FinancialItem> getExpense(String id) {
        return Optional.ofNullable(this.map.get(id));
    }

    public void removeExpense(String id) {
        this.map.remove(id);
    }

    /**
     * @return 取得所有支出項目
     */
    public Collection<FinancialItem> getAllExpenses() {
        return Collections.unmodifiableCollection(this.map.values());
    }

    /**
     * @return 總支出金額
     */
    public BigDecimal getTotalExpenseAmount() {
        return map.values().stream().map(FinancialItem::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return "ExpenseStatement=" + map.values() + '}';
    }
}

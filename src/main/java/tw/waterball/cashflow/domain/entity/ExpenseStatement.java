package tw.waterball.cashflow.domain.entity;

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

    @Override
    public String toString() {
        return "ExpenseStatement=" + map.values() + '}';
    }
}

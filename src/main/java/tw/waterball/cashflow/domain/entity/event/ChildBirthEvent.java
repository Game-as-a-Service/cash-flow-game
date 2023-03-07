package tw.waterball.cashflow.domain.entity.event;

import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.ExpenseStatement;
import tw.waterball.cashflow.domain.entity.FinancialItem;
import tw.waterball.cashflow.domain.entity.FinancialItemName;
import tw.waterball.cashflow.domain.entity.exception.InsufficientCashException;

import java.util.Map;
import java.util.Optional;

public class ChildBirthEvent implements Event {
    @Override
    public EventType getEventType() {
        return EventType.CHILD;
    }

    @Override
    public void execute(Actor actor, Map<String, Object> param) throws InsufficientCashException {
        ExpenseStatement expenseStmt = actor.getFinancialStatementV2().getExpense();
        Optional<FinancialItem> optChildExpense = expenseStmt.getExpense(FinancialItemName.CHILD.name());

        if(optChildExpense.isPresent()) { // 已有小孩支出項目
            FinancialItem childExpense = optChildExpense.get();
            if(childExpense.getCount() >= 3) {
                // 孩子數不能超過 3 個
                return;
            }
            childExpense.setCount(childExpense.getCount() + 1);
        }
        else { // 尚未有小孩就新增一個小孩支出項目
            expenseStmt.addExpense(FinancialItem.builder(FinancialItemName.CHILD.name(),
                                                         FinancialItemName.CHILD,
                                                         actor.getCareer().getChildExpense()).build());
        }
    }
}

package tw.waterball.cashflow.application.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.FinancialStatement;
import tw.waterball.cashflow.domain.entity.expense.Expense;
import tw.waterball.cashflow.domain.entity.expense.ExpenseType;
import tw.waterball.cashflow.domain.entity.liability.Liability;
import tw.waterball.cashflow.domain.entity.liability.LiabilityType;

import java.math.BigDecimal;

@Slf4j
public class RepayMoneyUseCase {
    final BigDecimal CASH_LOAN_INTEREST_RATIO = BigDecimal.valueOf(0.1);

    public Actor repayMoney(Actor actor, String liabilityTypeString, BigDecimal moneyAmount) {
        log.debug("In RepayMoneyUseCase");
        if (StringUtils.hasText(liabilityTypeString)) {
            throw new RuntimeException("Input parameter liabilityType String is blank");
        }
        if (isNullOrZero(moneyAmount)) {
            throw new RuntimeException("Input parameter moneyAmount is null or zero");
        }
        actor.setFinancialStatement(financialStatementUpdate(actor.getFinancialStatement(), liabilityTypeString, moneyAmount));
        log.debug("Finish RepayMoneyUseCase");
        return actor;
    }

    private FinancialStatement financialStatementUpdate(FinancialStatement financialStatement, String liabilityTypeString, BigDecimal moneyAmount) {
        LiabilityType liabilityType = LiabilityType.valueOf(liabilityTypeString);
        ExpenseType expenseType = ExpenseType.getExpenseTypeByLiabilityType(liabilityTypeString);
        BigDecimal liabilityAmount = financialStatement.getLiability(liabilityType).orElseThrow(() -> new RuntimeException("ExpenseType is not exist")).getAmount();

        BigDecimal liabilityResultAmount = liabilityAmount.subtract(moneyAmount);
        if (liabilityResultAmount.equals(BigDecimal.ZERO)) {
            financialStatement.deleteExpense(expenseType);
            financialStatement.deleteLiability(liabilityType);
        } else { // only CashLoan has this situation
            financialStatement.addExpense(Expense.builder(expenseType).amount(liabilityResultAmount.multiply(CASH_LOAN_INTEREST_RATIO)).build());
            financialStatement.addLiability(Liability.builder(liabilityType).amount(liabilityResultAmount).build());
        }
        financialStatement.subtractCash(moneyAmount);
        return financialStatement;
    }

    private boolean isNullOrZero(BigDecimal number) {
        boolean isBigDecimalValueNullOrZero = false;
        if (number == null) {
            isBigDecimalValueNullOrZero = true;
        } else if (number.compareTo(BigDecimal.ZERO) == 0) {
            isBigDecimalValueNullOrZero = true;
        }
        return isBigDecimalValueNullOrZero;
    }
}

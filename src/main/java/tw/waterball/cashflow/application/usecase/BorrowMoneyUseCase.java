package tw.waterball.cashflow.application.usecase;

import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.FinancialStatement;
import tw.waterball.cashflow.domain.entity.expense.Expense;
import tw.waterball.cashflow.domain.entity.expense.ExpenseType;
import tw.waterball.cashflow.domain.entity.liability.Liability;
import tw.waterball.cashflow.domain.entity.liability.LiabilityType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class BorrowMoneyUseCase {
    final BigDecimal CASH_LOAN_INTEREST_RATIO = BigDecimal.valueOf(0.1);

    public Actor borrowMoney(Actor actor, BigDecimal moneyAmount) {
        actor.setFinancialStatement(financialStatementUpdate(actor.getFinancialStatement(), moneyAmount));
        return actor;
    }

    private FinancialStatement financialStatementUpdate(FinancialStatement financialStatement, BigDecimal moneyAmount) {
        Optional<Liability> cashLoanLiabilityOptional = financialStatement.getLiability(LiabilityType.CashLoan);
        BigDecimal totalCashLoanLiability = cashLoanLiabilityOptional.isPresent() ? cashLoanLiabilityOptional.get().getAmount().add(moneyAmount) : moneyAmount;
        financialStatement.addLiability(Liability.builder(LiabilityType.CashLoan).amount(totalCashLoanLiability).build());

        Optional<Expense> cashLoanExpenseOptional = financialStatement.getExpense(ExpenseType.CashLoanPayment);
        BigDecimal totalCashLoanExpense = cashLoanExpenseOptional.isPresent() ? cashLoanExpenseOptional.get().getAmount().add((moneyAmount.multiply(CASH_LOAN_INTEREST_RATIO))) : moneyAmount.multiply(CASH_LOAN_INTEREST_RATIO);
        financialStatement.addExpense(Expense.builder(ExpenseType.CashLoanPayment).amount(totalCashLoanExpense.setScale(0, RoundingMode.HALF_UP)).build());

        return financialStatement;
    }
}

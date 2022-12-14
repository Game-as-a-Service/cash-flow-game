package tw.waterball.cashflow.usecase;

import tw.waterball.cashflow.domain.entity.FinancialStatement;
import tw.waterball.cashflow.domain.entity.expense.Expense;
import tw.waterball.cashflow.domain.entity.expense.ExpenseType;
import tw.waterball.cashflow.domain.entity.liability.Liability;
import tw.waterball.cashflow.domain.entity.liability.LiabilityType;

import java.math.BigDecimal;

public class GetCashLoanUseCase {
    final BigDecimal CASH_LOAN_RATIO = BigDecimal.valueOf(10);
    final BigDecimal CASH_LOAN_EXPENSE_RATIO = BigDecimal.valueOf(0.1);

    public Boolean getCashLoan(FinancialStatement financialStatement, BigDecimal cashLoanAmount) {
        if (isBankLoanAmountValid(financialStatement, cashLoanAmount)) {
            financialStatementUpdate(financialStatement, cashLoanAmount);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    private void financialStatementUpdate(FinancialStatement financialStatement, BigDecimal cashLoanAmount) {
        financialStatement.addLiability(Liability.builder(LiabilityType.CashLoan).amount(cashLoanAmount).build());
        financialStatement.addExpense(Expense.builder(ExpenseType.CashLoanPayment).amount((cashLoanAmount.multiply(CASH_LOAN_EXPENSE_RATIO))).build());
    }

    private boolean isBankLoanAmountValid(FinancialStatement financialStatement, BigDecimal cashLoanAmount) {
        return cashLoanAmount.compareTo(financialStatement.getPayday().multiply(CASH_LOAN_RATIO)) <= 0;
    }
}

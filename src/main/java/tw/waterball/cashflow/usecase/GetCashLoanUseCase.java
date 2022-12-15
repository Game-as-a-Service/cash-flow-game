package tw.waterball.cashflow.usecase;

import tw.waterball.cashflow.domain.entity.FinancialStatement;
import tw.waterball.cashflow.domain.entity.expense.Expense;
import tw.waterball.cashflow.domain.entity.expense.ExpenseType;
import tw.waterball.cashflow.domain.entity.liability.Liability;
import tw.waterball.cashflow.domain.entity.liability.LiabilityType;

import java.math.BigDecimal;
import java.util.Optional;

public class GetCashLoanUseCase {
    final BigDecimal CASH_LOAN_RATIO = BigDecimal.valueOf(10);
    final BigDecimal CASH_LOAN_EXPENSE_RATIO = BigDecimal.valueOf(0.1);

    public void execute() {
        //
    }

    public Boolean getCashLoan(FinancialStatement financialStatement, BigDecimal cashLoanAmount) {
        if (isBankLoanAmountValid(financialStatement, cashLoanAmount)) {
            financialStatementUpdate(financialStatement, cashLoanAmount);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    private void financialStatementUpdate(FinancialStatement financialStatement, BigDecimal cashLoanAmount) {
        // Liability
        Optional<Liability> cashLoanLiabilityOptional = financialStatement.getLiability(LiabilityType.CashLoan);
        BigDecimal totalCashLoanLiability = cashLoanLiabilityOptional.isPresent() ? cashLoanLiabilityOptional.get().getAmount().add(cashLoanAmount) : cashLoanAmount;
        financialStatement.addLiability(Liability.builder(LiabilityType.CashLoan).amount(totalCashLoanLiability).build());

        // Expense
        Optional<Expense> cashLoanExpenseOptional = financialStatement.getExpense(ExpenseType.CashLoanPayment);
        BigDecimal totalCashLoanExpense = cashLoanExpenseOptional.isPresent() ? cashLoanExpenseOptional.get().getAmount().add((cashLoanAmount.multiply(CASH_LOAN_EXPENSE_RATIO))) : cashLoanAmount.multiply(CASH_LOAN_EXPENSE_RATIO);
        financialStatement.addExpense(Expense.builder(ExpenseType.CashLoanPayment).amount(totalCashLoanExpense).build());
    }

    private boolean isBankLoanAmountValid(FinancialStatement financialStatement, BigDecimal cashLoanAmount) {
        return cashLoanAmount.compareTo(financialStatement.getPayday().multiply(CASH_LOAN_RATIO)) <= 0;
    }
}

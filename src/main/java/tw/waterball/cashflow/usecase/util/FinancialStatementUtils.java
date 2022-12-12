package tw.waterball.cashflow.usecase.util;

import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.domain.entity.FinancialStatement;
import tw.waterball.cashflow.domain.entity.expense.Expense;
import tw.waterball.cashflow.domain.entity.expense.ExpenseType;
import tw.waterball.cashflow.domain.entity.income.Income;
import tw.waterball.cashflow.domain.entity.income.IncomeType;
import tw.waterball.cashflow.domain.entity.liability.Liability;
import tw.waterball.cashflow.domain.entity.liability.LiabilityType;

import java.math.BigDecimal;

public class FinancialStatementUtils {
    public static FinancialStatement initialize(Career career) {
        FinancialStatement financialStatement;
        switch (career) {
            case Engineer:
                financialStatement = new FinancialStatement();
                financialStatement.addIncome(Income.builder(IncomeType.Salary).amount(BigDecimal.valueOf(2500)).build());

                financialStatement.addExpense(Expense.builder(ExpenseType.Tax).amount(BigDecimal.valueOf(460)).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.HomeMortgagePayment).amount(BigDecimal.valueOf(400)).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.CarLoanPayment).amount(BigDecimal.valueOf(80)).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.CreditCardPayment).amount(BigDecimal.valueOf(100)).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.OtherExpenses).amount(BigDecimal.valueOf(550)).build());

                financialStatement.addLiability(Liability.builder(LiabilityType.HomeMortgage).amount(BigDecimal.valueOf(38000)).build());
                financialStatement.addLiability(Liability.builder(LiabilityType.CarLoans).amount(BigDecimal.valueOf(4000)).build());
                financialStatement.addLiability(Liability.builder(LiabilityType.CreditCard).amount(BigDecimal.valueOf(3000)).build());
                return financialStatement;
            case Teacher:
                financialStatement = new FinancialStatement();
                financialStatement.addIncome(Income.builder(IncomeType.Salary).amount(BigDecimal.valueOf(1500)).build());

                financialStatement.addExpense(Expense.builder(ExpenseType.Tax).amount(BigDecimal.valueOf(400)).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.HomeMortgagePayment).amount(BigDecimal.valueOf(300)).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.CreditCardPayment).amount(BigDecimal.valueOf(100)).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.OtherExpenses).amount(BigDecimal.valueOf(200)).build());

                financialStatement.addLiability(Liability.builder(LiabilityType.HomeMortgage).amount(BigDecimal.valueOf(20000)).build());
                financialStatement.addLiability(Liability.builder(LiabilityType.CreditCard).amount(BigDecimal.valueOf(5000)).build());
                return financialStatement;
            default:
                throw new UnsupportedOperationException("Not implemented yet.");
        }
    }
}

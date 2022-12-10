package tw.waterball.cashflow.usecase.util;

import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.domain.entity.FinancialStatement;
import tw.waterball.cashflow.domain.entity.expense.Expense;
import tw.waterball.cashflow.domain.entity.expense.ExpenseType;
import tw.waterball.cashflow.domain.entity.income.Income;
import tw.waterball.cashflow.domain.entity.income.IncomeType;
import tw.waterball.cashflow.domain.entity.liability.Liability;
import tw.waterball.cashflow.domain.entity.liability.LiabilityType;

public class FinancialStatementUtils {
    public static FinancialStatement initialize(Career career) {
        FinancialStatement financialStatement;
        switch (career) {
            case Engineer:
                financialStatement = new FinancialStatement();
                financialStatement.addIncome(Income.builder(IncomeType.Salary).amount(2500).build());

                financialStatement.addExpense(Expense.builder(ExpenseType.Tax).amount(460).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.HomeMortgagePayment).amount(400).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.CarLoanPayment).amount(80).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.CreditCardPayment).amount(100).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.OtherExpenses).amount(550).build());

                financialStatement.addLiability(Liability.builder(LiabilityType.HomeMortgage).amount(38000).build());
                financialStatement.addLiability(Liability.builder(LiabilityType.CarLoans).amount(4000).build());
                financialStatement.addLiability(Liability.builder(LiabilityType.CreditCard).amount(3000).build());
                return financialStatement;
            case Teacher:
                financialStatement = new FinancialStatement();
                financialStatement.addIncome(Income.builder(IncomeType.Salary).amount(1500).build());

                financialStatement.addExpense(Expense.builder(ExpenseType.Tax).amount(400).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.HomeMortgagePayment).amount(300).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.CreditCardPayment).amount(100).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.OtherExpenses).amount(200).build());

                financialStatement.addLiability(Liability.builder(LiabilityType.HomeMortgage).amount(20000).build());
                financialStatement.addLiability(Liability.builder(LiabilityType.CreditCard).amount(5000).build());
                return financialStatement;
            default:
                throw new UnsupportedOperationException("Not implemented yet.");
        }
    }
}

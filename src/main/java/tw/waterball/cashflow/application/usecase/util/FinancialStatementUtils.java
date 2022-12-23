package tw.waterball.cashflow.application.usecase.util;

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
                financialStatement.addIncome(Income.builder(IncomeType.SALARY).amount(BigDecimal.valueOf(2500)).build());

                financialStatement.addExpense(Expense.builder(ExpenseType.TAX).amount(BigDecimal.valueOf(460)).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.HOME_MORTGAGE_PAYMENT).amount(BigDecimal.valueOf(400)).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.CAR_LOAN_PAYMENT).amount(BigDecimal.valueOf(80)).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.CREDIT_CARD_PAYMENT).amount(BigDecimal.valueOf(100)).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.OTHER_EXPENSES).amount(BigDecimal.valueOf(550)).build());

                financialStatement.addLiability(Liability.builder(LiabilityType.HOME_MORTGAGE).amount(BigDecimal.valueOf(38000)).build());
                financialStatement.addLiability(Liability.builder(LiabilityType.CAR_LOANS).amount(BigDecimal.valueOf(4000)).build());
                financialStatement.addLiability(Liability.builder(LiabilityType.CREDIT_CARD).amount(BigDecimal.valueOf(3000)).build());

                financialStatement.addCash(BigDecimal.valueOf(5000));
                return financialStatement;
            case Teacher:
                financialStatement = new FinancialStatement();
                financialStatement.addIncome(Income.builder(IncomeType.SALARY).amount(BigDecimal.valueOf(1500)).build());

                financialStatement.addExpense(Expense.builder(ExpenseType.TAX).amount(BigDecimal.valueOf(400)).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.HOME_MORTGAGE_PAYMENT).amount(BigDecimal.valueOf(300)).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.CREDIT_CARD_PAYMENT).amount(BigDecimal.valueOf(100)).build());
                financialStatement.addExpense(Expense.builder(ExpenseType.OTHER_EXPENSES).amount(BigDecimal.valueOf(200)).build());

                financialStatement.addLiability(Liability.builder(LiabilityType.HOME_MORTGAGE).amount(BigDecimal.valueOf(20000)).build());
                financialStatement.addLiability(Liability.builder(LiabilityType.CREDIT_CARD).amount(BigDecimal.valueOf(5000)).build());

                financialStatement.addCash(BigDecimal.valueOf(3000));
                return financialStatement;
            default:
                throw new UnsupportedOperationException("Not implemented yet.");
        }
    }
}

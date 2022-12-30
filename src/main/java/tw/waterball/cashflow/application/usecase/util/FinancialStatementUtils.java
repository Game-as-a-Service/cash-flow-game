package tw.waterball.cashflow.application.usecase.util;

import tw.waterball.cashflow.domain.entity.AssetStatement;
import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.domain.entity.ExpenseStatement;
import tw.waterball.cashflow.domain.entity.FinancialItem;
import tw.waterball.cashflow.domain.entity.FinancialItemName;
import tw.waterball.cashflow.domain.entity.FinancialStatement;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;
import tw.waterball.cashflow.domain.entity.IncomeStatement;
import tw.waterball.cashflow.domain.entity.LiabilityStatement;
import tw.waterball.cashflow.domain.entity.expense.Expense;
import tw.waterball.cashflow.domain.entity.expense.ExpenseType;
import tw.waterball.cashflow.domain.entity.income.Income;
import tw.waterball.cashflow.domain.entity.income.IncomeType;
import tw.waterball.cashflow.domain.entity.liability.Liability;
import tw.waterball.cashflow.domain.entity.liability.LiabilityType;

import java.math.BigDecimal;

public class FinancialStatementUtils {
    private FinancialStatementUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * @deprecated
     * @see #initializeV2(Career)
     */
    @Deprecated
    public static FinancialStatement initialize(Career career) {
        FinancialStatement financialStatement;
        switch (career) {
            case ENGINEER:
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
            case TEACHER:
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

    /**
     * 依照職業，取得初始化財務物件。
     * @param career 職業
     * @return 初始化財務
     */
    public static FinancialStatementV2 initializeV2(Career career) {
        FinancialStatementV2 financialStatement;
        IncomeStatement income;
        ExpenseStatement expense = new ExpenseStatement();
        LiabilityStatement liability = new LiabilityStatement();
        AssetStatement asset = new AssetStatement();
        switch (career) {
            case ENGINEER:
                income = new IncomeStatement(FinancialItem.builder(FinancialItemName.SALARY_ENGINEER.toString(), FinancialItemName.SALARY_ENGINEER, BigDecimal.valueOf(2500)).build());
                expense.addExpense(FinancialItem.builder(FinancialItemName.TAX.toString(), FinancialItemName.TAX, BigDecimal.valueOf(460)).build());
                expense.addExpense(FinancialItem.builder(FinancialItemName.HOME_MORTGAGE.toString(), FinancialItemName.HOME_MORTGAGE, BigDecimal.valueOf(400)).build());
                expense.addExpense(FinancialItem.builder(FinancialItemName.CAR_LOAN.toString(), FinancialItemName.CAR_LOAN, BigDecimal.valueOf(80)).build());
                expense.addExpense(FinancialItem.builder(FinancialItemName.CREDIT_CARD.toString(), FinancialItemName.CREDIT_CARD, BigDecimal.valueOf(100)).build());
                expense.addExpense(FinancialItem.builder(FinancialItemName.OTHER.toString(), FinancialItemName.OTHER, BigDecimal.valueOf(550)).build());
                liability.addBasicLiability(FinancialItem.builder(FinancialItemName.HOME_MORTGAGE.toString(), FinancialItemName.HOME_MORTGAGE, BigDecimal.valueOf(38000)).build());
                liability.addBasicLiability(FinancialItem.builder(FinancialItemName.CAR_LOAN.toString(), FinancialItemName.CAR_LOAN, BigDecimal.valueOf(4000)).build());
                liability.addBasicLiability(FinancialItem.builder(FinancialItemName.CREDIT_CARD.toString(), FinancialItemName.CREDIT_CARD, BigDecimal.valueOf(3000)).build());
                financialStatement = new FinancialStatementV2(income, expense, asset, liability);
                financialStatement.addCash(BigDecimal.valueOf(5000));
                break;
            case TEACHER:
                income = new IncomeStatement(FinancialItem.builder(FinancialItemName.SALARY_TEACHER_K_12.toString(), FinancialItemName.SALARY_TEACHER_K_12, BigDecimal.valueOf(1500)).build());
                expense.addExpense(FinancialItem.builder(FinancialItemName.TAX.toString(), FinancialItemName.TAX, BigDecimal.valueOf(400)).build());
                expense.addExpense(FinancialItem.builder(FinancialItemName.HOME_MORTGAGE.toString(), FinancialItemName.HOME_MORTGAGE, BigDecimal.valueOf(300)).build());
                expense.addExpense(FinancialItem.builder(FinancialItemName.CREDIT_CARD.toString(), FinancialItemName.CREDIT_CARD, BigDecimal.valueOf(100)).build());
                expense.addExpense(FinancialItem.builder(FinancialItemName.OTHER.toString(), FinancialItemName.OTHER, BigDecimal.valueOf(200)).build());
                liability.addBasicLiability(FinancialItem.builder(FinancialItemName.HOME_MORTGAGE.toString(), FinancialItemName.HOME_MORTGAGE, BigDecimal.valueOf(20000)).build());
                liability.addBasicLiability(FinancialItem.builder(FinancialItemName.CREDIT_CARD.toString(), FinancialItemName.CREDIT_CARD, BigDecimal.valueOf(5000)).build());
                financialStatement = new FinancialStatementV2(income, expense, asset, liability);
                financialStatement.addCash(BigDecimal.valueOf(3000));
                break;
            default:
                throw new UnsupportedOperationException("Not implemented yet.");
        }
        return financialStatement;
    }
}

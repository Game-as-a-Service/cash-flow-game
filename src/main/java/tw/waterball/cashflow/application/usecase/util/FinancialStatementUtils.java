package tw.waterball.cashflow.application.usecase.util;

import tw.waterball.cashflow.domain.entity.AssetStatement;
import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.domain.entity.ExpenseStatement;
import tw.waterball.cashflow.domain.entity.FinancialItem;
import tw.waterball.cashflow.domain.entity.FinancialItemName;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;
import tw.waterball.cashflow.domain.entity.IncomeStatement;
import tw.waterball.cashflow.domain.entity.LiabilityStatement;

import java.math.BigDecimal;

public class FinancialStatementUtils {
    private FinancialStatementUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 依照職業，取得初始化財務物件。
     *
     * @param career 職業
     * @return 初始化財務
     */
    public static FinancialStatementV2 initializeV2(Career career) {
        FinancialStatementV2 financialStatement;
        IncomeStatement income;
        ExpenseStatement expense = new ExpenseStatement();
        LiabilityStatement liability = new LiabilityStatement();
        AssetStatement asset = new AssetStatement();
        BigDecimal salary;
        BigDecimal expenseTax;
        BigDecimal expenseHomeMortgage;
        BigDecimal expenseCarLoan;
        BigDecimal expenseCreditCard;
        BigDecimal expenseRetail = BigDecimal.valueOf(50);
        BigDecimal expenseOther;
        BigDecimal liabilityHomeMortgage;
        BigDecimal liabilityCarLoan;
        BigDecimal liabilityCreditCard;
        BigDecimal liabilityRetail = BigDecimal.valueOf(1_000);
        BigDecimal cash;
        switch (career) {
            case ENGINEER:
                salary = BigDecimal.valueOf(4_900);
                expenseTax = BigDecimal.valueOf(1_050);
                expenseHomeMortgage = BigDecimal.valueOf(700);
                expenseCarLoan = BigDecimal.valueOf(140);
                expenseCreditCard = BigDecimal.valueOf(120);
                expenseOther = BigDecimal.valueOf(1_090);
                liabilityHomeMortgage = BigDecimal.valueOf(75_000);
                liabilityCarLoan = BigDecimal.valueOf(7_000);
                liabilityCreditCard = BigDecimal.valueOf(4_000);
                cash = BigDecimal.valueOf(400);
                break;
            case TEACHER:
                salary = BigDecimal.valueOf(3300);
                expenseTax = BigDecimal.valueOf(630);
                expenseHomeMortgage = BigDecimal.valueOf(500);
                expenseCarLoan = BigDecimal.valueOf(100);
                expenseCreditCard = BigDecimal.valueOf(90);
                expenseOther = BigDecimal.valueOf(760);
                liabilityHomeMortgage = BigDecimal.valueOf(50_000);
                liabilityCarLoan = BigDecimal.valueOf(5_000);
                liabilityCreditCard = BigDecimal.valueOf(3_000);
                cash = BigDecimal.valueOf(400);
                break;
            case SECRETARY:
                salary = BigDecimal.valueOf(2500);
                expenseTax = BigDecimal.valueOf(460);
                expenseHomeMortgage = BigDecimal.valueOf(400);
                expenseCarLoan = BigDecimal.valueOf(80);
                expenseCreditCard = BigDecimal.valueOf(60);
                expenseOther = BigDecimal.valueOf(570);
                liabilityHomeMortgage = BigDecimal.valueOf(38000);
                liabilityCarLoan = BigDecimal.valueOf(4000);
                liabilityCreditCard = BigDecimal.valueOf(2000);
                cash = BigDecimal.valueOf(710);
                break;
            case TRUCK_DRIVER:
                salary = BigDecimal.valueOf(2500);
                expenseTax = BigDecimal.valueOf(460);
                expenseHomeMortgage = BigDecimal.valueOf(400);
                expenseCarLoan = BigDecimal.valueOf(80);
                expenseCreditCard = BigDecimal.valueOf(60);
                expenseOther = BigDecimal.valueOf(570);
                liabilityHomeMortgage = BigDecimal.valueOf(38000);
                liabilityCarLoan = BigDecimal.valueOf(4000);
                liabilityCreditCard = BigDecimal.valueOf(2000);
                cash = BigDecimal.valueOf(750);
                break;
            case PILOT:
                salary = BigDecimal.valueOf(9500);
                expenseTax = BigDecimal.valueOf(2350);
                expenseHomeMortgage = BigDecimal.valueOf(1330);
                expenseCarLoan = BigDecimal.valueOf(300);
                expenseCreditCard = BigDecimal.valueOf(660);
                expenseOther = BigDecimal.valueOf(2210);
                liabilityHomeMortgage = BigDecimal.valueOf(143_000);
                liabilityCarLoan = BigDecimal.valueOf(15_000);
                liabilityCreditCard = BigDecimal.valueOf(22_000);
                cash = BigDecimal.valueOf(400);
                break;
            case NURSE:
                salary = BigDecimal.valueOf(3_100);
                expenseTax = BigDecimal.valueOf(600);
                expenseHomeMortgage = BigDecimal.valueOf(400);
                expenseCarLoan = BigDecimal.valueOf(100);
                expenseCreditCard = BigDecimal.valueOf(90);
                expenseOther = BigDecimal.valueOf(710);
                liabilityHomeMortgage = BigDecimal.valueOf(47_000);
                liabilityCarLoan = BigDecimal.valueOf(5_000);
                liabilityCreditCard = BigDecimal.valueOf(3_000);
                cash = BigDecimal.valueOf(480);
                break;
            case DOCTOR:
                salary = BigDecimal.valueOf(13_200);
                expenseTax = BigDecimal.valueOf(3_420);
                expenseHomeMortgage = BigDecimal.valueOf(1_900);
                expenseCarLoan = BigDecimal.valueOf(380);
                expenseCreditCard = BigDecimal.valueOf(270);
                expenseOther = BigDecimal.valueOf(2_880);
                liabilityHomeMortgage = BigDecimal.valueOf(202_000);
                liabilityCarLoan = BigDecimal.valueOf(19_000);
                liabilityCreditCard = BigDecimal.valueOf(9_000);
                cash = BigDecimal.valueOf(400);
                break;
            case POLICE:
                salary = BigDecimal.valueOf(3000);
                expenseTax = BigDecimal.valueOf(580);
                expenseHomeMortgage = BigDecimal.valueOf(400);
                expenseCarLoan = BigDecimal.valueOf(100);
                expenseCreditCard = BigDecimal.valueOf(60);
                expenseOther = BigDecimal.valueOf(290);
                liabilityHomeMortgage = BigDecimal.valueOf(46000);
                liabilityCarLoan = BigDecimal.valueOf(5000);
                liabilityCreditCard = BigDecimal.valueOf(2000);
                cash = BigDecimal.valueOf(520);
                break;
            case LAWYER:
                salary = BigDecimal.valueOf(7500);
                expenseTax = BigDecimal.valueOf(1830);
                expenseHomeMortgage = BigDecimal.valueOf(1100);
                expenseCarLoan = BigDecimal.valueOf(220);
                expenseCreditCard = BigDecimal.valueOf(180);
                expenseOther = BigDecimal.valueOf(1650);
                liabilityHomeMortgage = BigDecimal.valueOf(115000);
                liabilityCarLoan = BigDecimal.valueOf(11000);
                liabilityCreditCard = BigDecimal.valueOf(6000);
                cash = BigDecimal.valueOf(400);
                break;
            default:
                throw new UnsupportedOperationException("Not implemented yet.");
        }

        income = new IncomeStatement(FinancialItem.builder(FinancialItemName.SALARY.toString(),
                                                           FinancialItemName.SALARY,
                                                           salary).build());
        expense.addExpense(FinancialItem.builder(FinancialItemName.TAX.toString(), FinancialItemName.TAX, expenseTax)
                                        .build());
        expense.addExpense(FinancialItem.builder(FinancialItemName.HOME_MORTGAGE.toString(),
                                                 FinancialItemName.HOME_MORTGAGE,
                                                 expenseHomeMortgage).build());
        expense.addExpense(FinancialItem.builder(FinancialItemName.CAR_LOAN.toString(),
                                                 FinancialItemName.CAR_LOAN,
                                                 expenseCarLoan).build());
        expense.addExpense(FinancialItem.builder(FinancialItemName.CREDIT_CARD.toString(),
                                                 FinancialItemName.CREDIT_CARD,
                                                 expenseCreditCard).build());
        expense.addExpense(FinancialItem.builder(FinancialItemName.RETAIL.toString(),
                                                 FinancialItemName.RETAIL,
                                                 expenseRetail).build());
        expense.addExpense(FinancialItem.builder(FinancialItemName.OTHER.toString(),
                                                 FinancialItemName.OTHER,
                                                 expenseOther).build());
        liability.addBasicLiability(FinancialItem.builder(FinancialItemName.HOME_MORTGAGE.toString(),
                                                          FinancialItemName.HOME_MORTGAGE,
                                                          liabilityHomeMortgage).build());
        liability.addBasicLiability(FinancialItem.builder(FinancialItemName.CAR_LOAN.toString(),
                                                          FinancialItemName.CAR_LOAN,
                                                          liabilityCarLoan).build());
        liability.addBasicLiability(FinancialItem.builder(FinancialItemName.CREDIT_CARD.toString(),
                                                          FinancialItemName.CREDIT_CARD,
                                                          liabilityCreditCard).build());
        liability.addBasicLiability(FinancialItem.builder(FinancialItemName.RETAIL.toString(),
                                                          FinancialItemName.RETAIL,
                                                          liabilityRetail).build());
        financialStatement = new FinancialStatementV2(income, expense, asset, liability);
        financialStatement.addCash(cash);
        return financialStatement;
    }
}

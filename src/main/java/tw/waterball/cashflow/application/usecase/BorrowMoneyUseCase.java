package tw.waterball.cashflow.application.usecase;

import lombok.extern.slf4j.Slf4j;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.FinancialStatement;
import tw.waterball.cashflow.domain.entity.expense.Expense;
import tw.waterball.cashflow.domain.entity.expense.ExpenseType;
import tw.waterball.cashflow.domain.entity.liability.Liability;
import tw.waterball.cashflow.domain.entity.liability.LiabilityType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Slf4j
public class BorrowMoneyUseCase {
    final BigDecimal CASH_LOAN_INTEREST_RATIO = BigDecimal.valueOf(0.1);

    public Actor borrowMoney(Actor actor, BigDecimal moneyAmount) {
        log.debug("In BorrowMoneyUseCase");
        if (isNullOrZero(moneyAmount)) {
            throw new RuntimeException("Input parameter moneyAmount is null or zero");
        }
        actor.setFinancialStatement(financialStatementUpdate(actor.getFinancialStatement(), moneyAmount));
        log.debug("Finish BorrowMoneyUseCase");
        return actor;
    }

    private FinancialStatement financialStatementUpdate(FinancialStatement financialStatement, BigDecimal moneyAmount) {
        Optional<Liability> cashLoanLiabilityOptional = financialStatement.getLiability(LiabilityType.CashLoan);
        BigDecimal totalCashLoanLiability = cashLoanLiabilityOptional.map(liability -> liability.getAmount().add(moneyAmount)).orElse(moneyAmount);
        financialStatement.addLiability(Liability.builder(LiabilityType.CashLoan).amount(totalCashLoanLiability).build());

        Optional<Expense> cashLoanExpenseOptional = financialStatement.getExpense(ExpenseType.CashLoanPayment);
        BigDecimal totalCashLoanExpense = cashLoanExpenseOptional.map(expense -> expense.getAmount().add((moneyAmount.multiply(CASH_LOAN_INTEREST_RATIO)))).orElseGet(() -> moneyAmount.multiply(CASH_LOAN_INTEREST_RATIO));
        financialStatement.addExpense(Expense.builder(ExpenseType.CashLoanPayment).amount(totalCashLoanExpense.setScale(0, RoundingMode.HALF_UP)).build());

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

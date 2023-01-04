package tw.waterball.cashflow.application.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tw.waterball.cashflow.application.repository.ActorRepository;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.FinancialStatement;
import tw.waterball.cashflow.domain.entity.exception.ActorNotFound;
import tw.waterball.cashflow.domain.entity.expense.Expense;
import tw.waterball.cashflow.domain.entity.expense.ExpenseType;
import tw.waterball.cashflow.domain.entity.liability.Liability;
import tw.waterball.cashflow.domain.entity.liability.LiabilityType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Slf4j
@Service
public class RepayMoneyUseCase {

    final BigDecimal CASH_LOAN_INTEREST_RATIO = BigDecimal.valueOf(0.1);
    @Autowired
    private ActorRepository actorRepository;

    public Actor repayMoney(Actor actor, String liabilityTypeString, BigDecimal repayMoneyAmount) {
        log.debug("In RepayMoneyUseCase");
        Optional<Actor> actorOptional = actorRepository.findGameByNickname(actor.getActorName());
        if (actorOptional.isEmpty()) {
            throw new ActorNotFound("Actor is not exist");
        }
        if (!StringUtils.hasText(liabilityTypeString)) {
            throw new RuntimeException("Input parameter liabilityType String is blank");
        }
        if (isNullOrZero(repayMoneyAmount)) {
            throw new RuntimeException("Input parameter moneyAmount is null or zero");
        }
        if (isRepayMoneyAmountMoreThanCash(actor, repayMoneyAmount)) {
            throw new RuntimeException("Actor doesn't have enough cash");
        }
        actor.setFinancialStatement(financialStatementUpdate(actor.getFinancialStatement(), liabilityTypeString, repayMoneyAmount));
        log.debug("Finish RepayMoneyUseCase");
        return actorRepository.save(actor);
    }

    private FinancialStatement financialStatementUpdate(FinancialStatement financialStatement, String liabilityTypeString, BigDecimal moneyAmount) {
        LiabilityType liabilityType = LiabilityType.valueOf(liabilityTypeString);
        ExpenseType expenseType = ExpenseType.valueOf(LiabilityType.valueOf(liabilityTypeString).getExpenseTypeString());
        BigDecimal liabilityAmount = financialStatement.getLiability(liabilityType).orElseThrow(() -> new RuntimeException("ExpenseType is not exist")).getAmount();

        BigDecimal liabilityResultAmount = liabilityAmount.subtract(moneyAmount);
        if (liabilityResultAmount.equals(BigDecimal.ZERO)) {
            financialStatement.deleteExpense(expenseType);
            financialStatement.deleteLiability(liabilityType);
        } else { // only CashLoan has this situation
            financialStatement.updateExpense(Expense.builder(expenseType).amount(liabilityResultAmount.multiply(CASH_LOAN_INTEREST_RATIO).setScale(0, RoundingMode.HALF_UP)).build());
            financialStatement.updateLiability(Liability.builder(liabilityType).amount(liabilityResultAmount).build());
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

    private boolean isRepayMoneyAmountMoreThanCash(Actor actor, BigDecimal repayMoneyAmount) {
        return repayMoneyAmount.compareTo(actor.getFinancialStatement().getCash()) > 0;
    }
}

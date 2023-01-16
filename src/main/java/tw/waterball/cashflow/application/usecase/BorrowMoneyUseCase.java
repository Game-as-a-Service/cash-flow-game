package tw.waterball.cashflow.application.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tw.waterball.cashflow.application.repository.ActorRepository;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.ExpenseStatement;
import tw.waterball.cashflow.domain.entity.FinancialItem;
import tw.waterball.cashflow.domain.entity.FinancialItemName;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;
import tw.waterball.cashflow.domain.entity.LiabilityStatement;
import tw.waterball.cashflow.domain.entity.exception.ActorNotFound;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class BorrowMoneyUseCase {
    final BigDecimal CASH_LOAN_INTEREST_RATIO = BigDecimal.valueOf(0.1);
    @Autowired
    private ActorRepository actorRepository;

    public Actor borrowMoney(Actor actor, BigDecimal moneyAmount) {
        log.debug("In BorrowMoneyUseCase");
        Optional<Actor> actorOptional = actorRepository.findGameByActorName(actor.getActorName());
        if (actorOptional.isPresent()) {
            throw new ActorNotFound("Actor is not exist");
        }
        if (isNullOrZero(moneyAmount)) {
            throw new RuntimeException("Input parameter moneyAmount is null or zero");
        }
        financialStatementUpdate(actor.getFinancialStatementV2(), moneyAmount);
        log.debug("Finish BorrowMoneyUseCase");
        return actorRepository.save(actor);
//        return actor;
    }

    private void financialStatementUpdate(FinancialStatementV2 financialStatementV2, BigDecimal moneyAmount) {
        // 取得Liability LOAN的ID & FinancialItem
        String id = null;
        FinancialItem liabilityLoan = null;
        BigDecimal finalMoneyAmount = moneyAmount.multiply(CASH_LOAN_INTEREST_RATIO).setScale(0, RoundingMode.HALF_UP);
        LiabilityStatement liabilityStatement = financialStatementV2.getLiability();
        Collection<FinancialItem> basicLiabilities = liabilityStatement.getAllBasicLiabilities();

        for(FinancialItem financialItem : basicLiabilities){
            if(FinancialItemName.LOAN == financialItem.getName()){
                id = financialItem.getId();
                liabilityLoan = financialItem;
                break;
            }
        }
        // 更新Liability LOAN FinancialItem & Expense LOAN_PAYMENT FinancialItem (藉由ID取得)
        ExpenseStatement expenseStatement = financialStatementV2.getExpense();
        if(ObjectUtils.isEmpty(liabilityLoan)){
            id = UUID.randomUUID().toString();
            liabilityStatement.addBasicLiability(FinancialItem.builder(id, FinancialItemName.LOAN, moneyAmount).build());
            expenseStatement.addExpense(FinancialItem.builder(id, FinancialItemName.LOAN_PAYMENT, finalMoneyAmount).build());
        } else {
            liabilityLoan.setAmount(liabilityLoan.getAmount().add(moneyAmount));
            liabilityStatement.addBasicLiability(liabilityLoan);

            BigDecimal expenseLoanPaymentAmount= expenseStatement.getExpense(id).get().getAmount();
            expenseStatement.addExpense(FinancialItem.builder(id, FinancialItemName.LOAN_PAYMENT, expenseLoanPaymentAmount.add(finalMoneyAmount)).build());
        }
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

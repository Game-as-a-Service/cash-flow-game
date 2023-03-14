package tw.waterball.cashflow.application.usecase;

import lombok.RequiredArgsConstructor;
import tw.waterball.cashflow.application.repository.ActorRepository;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.FinancialItem;
import tw.waterball.cashflow.domain.entity.FinancialItemName;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;
import tw.waterball.cashflow.domain.entity.exception.ActorNotFound;

import java.math.BigDecimal;
import java.util.Objects;

@RequiredArgsConstructor
public class BorrowMoneyUseCase {
    private final ActorRepository actorRepo;

    /**
     * 進行借錢
     * @param actorId 要借錢的玩家 ID
     * @param borrowAmount 借多少錢
     * @throws ActorNotFound 從玩家 ID 找不到玩家資料
     * @throws IllegalArgumentException 若借的金額小於等於 0
     */
    public void borrowMoney(String actorId, BigDecimal borrowAmount) {
        if(Objects.isNull(borrowAmount) || BigDecimal.ONE.compareTo(borrowAmount) > 0) {
            throw new IllegalArgumentException("The amount must > 0");
        }

        Actor actor = actorRepo.findByActorId(actorId).orElseThrow(() -> new ActorNotFound(actorId));

        FinancialStatementV2 financialStmt = actor.getFinancialStatementV2();
        financialStmt.addCash(borrowAmount); // 儲蓄增加
        financialStmt.getLiability().addBasicLiability(FinancialItem.builder(FinancialItemName.LOAN.name(), // 負債增加
                                                                             FinancialItemName.LOAN,
                                                                             borrowAmount).build());
        financialStmt.getExpense().addExpense(FinancialItem.builder(FinancialItemName.LOAN.name(), // 支出增加要付 10% 的利息
                                                                    FinancialItemName.LOAN,
                                                                    borrowAmount.multiply(BigDecimal.valueOf(0.1))).build());
    }
}

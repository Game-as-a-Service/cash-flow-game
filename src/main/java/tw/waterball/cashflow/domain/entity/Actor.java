package tw.waterball.cashflow.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tw.waterball.cashflow.application.usecase.util.FinancialStatementUtils;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Actor {
    private String dream;
    private final String actorName;
    private final Career career;
    private FinancialStatement financialStatement;
    private int position = 0;
    /**
     * 角色目前狀態
     */
    @Getter
    @Setter
    private ActorState state = ActorState.NONE;
    private int diceCount = 1;
    private int turnNumber = 1;

    public Actor(final String actorName, final Career career) {
        this.actorName = actorName;
        this.career = career;
        this.financialStatement = FinancialStatementUtils.initialize(this.career);
    }

    public boolean isInOuterCircle() {
        return financialStatement.getPassiveIncome().compareTo(financialStatement.getTotalExpenseAmount()) >= 0;
    }
}

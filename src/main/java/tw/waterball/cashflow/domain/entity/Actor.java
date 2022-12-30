package tw.waterball.cashflow.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tw.waterball.cashflow.application.usecase.util.FinancialStatementUtils;

@ToString
@Getter
@EqualsAndHashCode
public class Actor {
    @Setter
    private String dream;

    private final String actorName;

    private final Career career;

    private FinancialStatement financialStatement;
    @Setter
    private int position = 0;

    /**
     * 角色目前狀態.
     */
    @Setter
    private ActorState state = ActorState.NONE;

    /**
     * 骰子顆數.
     */
    @Setter
    private int diceCount = 1;

    /**
     * 回合數，<br>
     * 例如角色狀態為失業，回合數為 2，表示失業狀態要還有 2 個回合。
     *
     * @see #getState()
     */
    @Setter
    private int turnNumber = 1;


    public Actor(final String actorName, final Career career) {
        this.actorName = actorName;
        this.career = career;
        this.financialStatement = FinancialStatementUtils.initialize(this.career);
    }

    public boolean isInOuterCircle() {
        return financialStatement.getPassiveIncome().compareTo(financialStatement.getTotalExpenseAmount()) >= 0;
    }

    /**
     * @deprecated Actor 建構式已針對職業做財務初始化了，故不建議使用此方法設定財務物件。
     */
    @Deprecated
    public void setFinancialStatement(FinancialStatement financialStatement) {
        this.financialStatement = financialStatement;
    }
}

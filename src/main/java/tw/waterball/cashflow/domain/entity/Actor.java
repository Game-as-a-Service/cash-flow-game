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
    private final String actorName;
    private final Career career;
    private FinancialStatement financialStatement;

    public Actor(String actorName, Career career) {
        this.actorName = actorName;
        this.career = career;
        this.financialStatement = FinancialStatementUtils.initialize(this.career);
    }
}

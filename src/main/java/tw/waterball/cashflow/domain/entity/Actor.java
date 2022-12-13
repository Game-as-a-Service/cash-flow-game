package tw.waterball.cashflow.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Actor {
    private final int playId;
//    private final String playName; TODO 跟 nickname 一樣??
    private String dream;
    private final String nickname;
    private final Career career;
//    private FinancialStatement financialStatement;

    public Actor(int playId, String nickname, Career career) {
        this.playId = playId;
        this.nickname = nickname;
        this.career = career;
//        this.financialStatement = FinancialStatementUtils.initialize(this.career);
    }
}

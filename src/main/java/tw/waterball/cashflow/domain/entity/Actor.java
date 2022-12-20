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
  private final String nickname;
  private final Career career;
  private FinancialStatement financialStatement;

  public boolean isInOuterCircle() {
    return financialStatement.getPassiveIncome().compareTo(financialStatement.getTotalExpenseAmount())>=0;
  }
  public Actor(String nickname, Career career) {
    this.nickname = nickname;
    this.career = career;
    this.financialStatement = FinancialStatementUtils.initialize(this.career);
  }
}

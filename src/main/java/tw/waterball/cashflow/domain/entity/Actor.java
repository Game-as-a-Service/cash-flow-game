package tw.waterball.cashflow.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tw.waterball.cashflow.application.usecase.util.FinancialStatementUtils;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Actor {
  private String dream;
  private final String nickname;
  private final Career career;
  private FinancialStatement financialStatement;
  private BigDecimal passiveIncome;
  private BigDecimal expenses;


  public void setPassiveIncome(FinancialStatement financialStatement) {
    passiveIncome = financialStatement.getPassiveIncome();
  }

  public void setExpenses(FinancialStatement financialStatement) {
    this.expenses = financialStatement.getTotalExpenseAmount();
  }

  public boolean isInOuterCircle() {
    return passiveIncome.compareTo(expenses)>=0;
  }
  public Actor(String nickname, Career career) {
    this.nickname = nickname;
    this.career = career;
    this.financialStatement = FinancialStatementUtils.initialize(this.career);
  }
}

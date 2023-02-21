package tw.waterball.cashflow.domain.entity.expense;

/**
 * @deprecated
 *
 * @see tw.waterball.cashflow.domain.entity.ExpenseStatement
 * @see tw.waterball.cashflow.domain.entity.FinancialItem
 * @see tw.waterball.cashflow.domain.entity.FinancialItemName
 * @see tw.waterball.cashflow.domain.entity.FinancialStatementV2
 */
@Deprecated(forRemoval = true)
public enum ExpenseType {
    /**
     * 利息
     */
    INTEREST,
    /**
     * 小孩支出
     */
    EDUCATION_EXPENSE,
    TAX,
    /**
     * 房貸
     */
    HOME_MORTGAGE_PAYMENT,
    /**
     * 車貸
     */
    CAR_LOAN_PAYMENT, CREDIT_CARD_PAYMENT, OTHER_EXPENSES
}

package tw.waterball.cashflow.domain.entity.expense;

public enum ExpenseType {
    /**
     * 利息
     */
    Interest,
    /**
     * 小孩支出
     */
    EducationExpense, Tax,
    /**
     * 房貸
     */
    HomeMortgagePayment,
    /**
     * 車貸
     */
    CarLoanPayment, CreditCardPayment, CashLoanPayment, OtherExpenses
}

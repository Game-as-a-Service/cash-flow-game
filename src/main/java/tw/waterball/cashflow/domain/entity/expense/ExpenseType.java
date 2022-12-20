package tw.waterball.cashflow.domain.entity.expense;

public enum ExpenseType {
    /**
     * 利息
     */
    Interest("Interest"),
    /**
     * 小孩支出
     */
    EducationExpense("EducationExpense"), Tax("Tax"),
    /**
     * 房貸
     */
    HomeMortgagePayment("HomeMortgage"),
    /**
     * 車貸
     */
    CarLoanPayment("CarLoans"), CreditCardPayment("CreditCard"), CashLoanPayment("CashLoan"), RetailPayment("RetailDebt"), OtherExpenses("OtherExpenses");

    private String liabilityType;

    private ExpenseType(String liabilityType) {
        this.liabilityType = liabilityType;
    }

    public static ExpenseType getExpenseTypeByLiabilityType(String liabilityType) {
        return ExpenseType.valueOf(liabilityType);
    }

}

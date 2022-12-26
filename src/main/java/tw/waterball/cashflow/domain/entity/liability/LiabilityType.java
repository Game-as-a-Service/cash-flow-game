package tw.waterball.cashflow.domain.entity.liability;

public enum LiabilityType {
    HomeMortgage("HomeMortgagePayment"), CarLoans("CarLoanPayment"), CreditCard("CreditCardPayment"), RetailDebt("RetailPayment"), CashLoan("CashLoanPayment");

    private String expenseTypeString;

    LiabilityType(String expenseTypeString) {
        this.expenseTypeString = expenseTypeString;
    }

    public String getExpenseTypeString() {
        return expenseTypeString;
    }

}

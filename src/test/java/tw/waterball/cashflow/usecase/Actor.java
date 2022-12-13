package tw.waterball.cashflow.usecase;

public class Actor {
    private int passiveIncome;
    private int expenses;


    public void setPassiveIncome(int income) {
        passiveIncome = income;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    public boolean isInOuterCircle() {
        return passiveIncome >= expenses;
    }
}

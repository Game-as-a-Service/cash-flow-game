package tw.waterball.cashflow.domain.entity;

import lombok.Getter;

import java.math.BigDecimal;

public enum Career {
    ENGINEER(1),
    TEACHER(1),
    POLICE(1),
    PILOT(1),
    LAWYER(380),
    SECRETARY(1),
    TRUCK_DRIVER(1),
    NURSE(1),
    DOCTOR(1);

    @Getter
    private final BigDecimal childExpense;

    Career(long childExpense) {
        this.childExpense = BigDecimal.valueOf(childExpense);
    }

}

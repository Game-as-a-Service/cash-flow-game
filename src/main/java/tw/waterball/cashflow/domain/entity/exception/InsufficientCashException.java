package tw.waterball.cashflow.domain.entity.exception;

/**
 * 儲蓄不足以扣款的錯誤
 */
public class InsufficientCashException extends Exception {
    public InsufficientCashException(String message) {
        super(message);
    }

    public InsufficientCashException() {
        super();
    }

}

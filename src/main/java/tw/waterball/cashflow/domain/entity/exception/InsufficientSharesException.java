package tw.waterball.cashflow.domain.entity.exception;

public class InsufficientSharesException extends RuntimeException {
    public InsufficientSharesException(String message) {
        super(message);
    }

    public InsufficientSharesException() {
        super();
    }
}

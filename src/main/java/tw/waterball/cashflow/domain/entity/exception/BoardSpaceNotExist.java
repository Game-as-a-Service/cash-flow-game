package tw.waterball.cashflow.domain.entity.exception;


public class BoardSpaceNotExist extends RuntimeException {

  public BoardSpaceNotExist(final String message) {
    super(message);
  }
}

package tw.waterball.cashflow.domain.entity.exception;


public class ActorNotFound extends RuntimeException {

  public ActorNotFound(final String message) {
    super(message);
  }
}

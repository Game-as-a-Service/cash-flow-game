package tw.waterball.cashflow.application.usecase;


import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.application.repository.ActorRepository;
import tw.waterball.cashflow.domain.entity.exception.ActorNotFound;

@Slf4j
@AllArgsConstructor
public class ChooseDreamUseCase {

  private final ActorRepository actorRepository;

  public void execute(Input input) {
    log.debug("In ChooseDreamUseCase");
    Actor inputActor = input.getActor();
    Optional<Actor> actor = actorRepository.findGameByActorId(inputActor.getPlayId());
    if (actor.isEmpty()) {
      throw new ActorNotFound(
          "[ActorId:ActorName]:[" + inputActor.getPlayId() + "]:[" + inputActor.getNickname()
              + "]");
    }
    log.debug("[ActorId:ActorName][{}:{}] save dream: {}", inputActor.getPlayId(),
        inputActor.getNickname(), input.getDream());
    inputActor.setDream(input.getDream());
    actorRepository.save(inputActor);
    log.debug("Finish ChooseDreamUseCase");
  }

  @Value
  public static class Input {

    public final Actor actor;
    public final int gameId; // TODO 這個應該要有
    public final String dream;
  }
}

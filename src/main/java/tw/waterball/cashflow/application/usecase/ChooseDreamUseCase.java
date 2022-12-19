package tw.waterball.cashflow.application.usecase;


import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import tw.waterball.cashflow.application.repository.ActorRepository;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.exception.ActorNotFound;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class ChooseDreamUseCase {

    private final ActorRepository actorRepository;

    public void execute(final Input input) {
        log.debug("In ChooseDreamUseCase");
        Actor inputActor = input.getActor();
        Optional<Actor> actor = actorRepository.findGameByNickname(inputActor.getActorName());
        if (actor.isEmpty()) {
            throw new ActorNotFound("[Nickname]:[" + inputActor.getActorName() + "]");
        }
        log.debug("[Nickname][{}] save dream: {}", inputActor.getActorName(), input.getDream());
        inputActor.setDream(input.getDream());
        actorRepository.save(inputActor);
        log.debug("Finish ChooseDreamUseCase");
    }

    @Value
    public static class Input {

        private Actor actor;
        private int gameId; // TODO 這個應該要有
        private String dream;
    }
}

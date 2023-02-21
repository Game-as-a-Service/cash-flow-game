package tw.waterball.cashflow.application.usecase;

import tw.waterball.cashflow.application.repository.ActorRepository;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.ActorState;
import tw.waterball.cashflow.domain.entity.ChessBoard;
import tw.waterball.cashflow.domain.entity.exception.ActorNotFound;

import java.util.Random;

/**
 * 擲骰子 use case
 */
public class RollDiceUseCase {
    private static Random dice = new Random();
    public void execute(String actorId, ActorRepository actorRepo) {
        Actor actor = actorRepo.findByActorId(actorId).orElseThrow(() -> new ActorNotFound(actorId));
        if(ActorState.MY_TURN == actor.getState()) {
            // 若使用 Java 17+，可使用 Random.nextInt(int, int)。
            int rolledNumber = dice.nextInt(actor.getDiceCount() * 6 + 1);
            ChessBoard.move(actor, rolledNumber == 0? actor.getDiceCount() : rolledNumber);
            actor.setState(ActorState.DICE_ROLLED);
            actorRepo.save(actor);
        }
    }
}

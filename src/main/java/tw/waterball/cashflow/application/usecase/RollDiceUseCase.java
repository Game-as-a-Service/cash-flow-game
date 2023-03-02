package tw.waterball.cashflow.application.usecase;

import lombok.RequiredArgsConstructor;
import tw.waterball.cashflow.application.repository.ActorRepository;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.ActorState;
import tw.waterball.cashflow.domain.entity.ChessBoard;
import tw.waterball.cashflow.domain.entity.event.Event;
import tw.waterball.cashflow.domain.entity.event.EventFactory;
import tw.waterball.cashflow.domain.entity.exception.ActorNotFound;

import java.util.Optional;
import java.util.Random;

/**
 * 擲骰子 use case
 */
@RequiredArgsConstructor
public class RollDiceUseCase {
    private static Random dice = new Random();
    private final ActorRepository actorRepo;
    public Optional<Event> rollDice(String actorId) {
        Actor actor = actorRepo.findByActorId(actorId).orElseThrow(() -> new ActorNotFound(actorId));
        if(ActorState.MY_TURN == actor.getState()) {
            int rolledNumber = dice.nextInt(actor.getDiceCount() * 6 + 1); // 若使用 Java 17+，可使用 Random.nextInt(int, int)。
            ChessBoard.move(actor, rolledNumber == 0? actor.getDiceCount() : rolledNumber);
            actor.setState(ActorState.DICE_ROLLED);
            actorRepo.save(actor);
            return Optional.of(EventFactory.getEvent(ChessBoard.getBoardEvent(actor.getPosition()))); // 回傳玩家在棋盤上的 event
        }
        return Optional.empty();
    }
}

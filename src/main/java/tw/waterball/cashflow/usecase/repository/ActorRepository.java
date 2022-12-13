package tw.waterball.cashflow.usecase.repository;

import java.util.Optional;
import tw.waterball.cashflow.domain.entity.Actor;

public interface ActorRepository {

  Optional<Actor> findGameByActorId(int actorId);

  Actor save(Actor chineseChess);
}

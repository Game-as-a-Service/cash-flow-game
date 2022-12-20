package tw.waterball.cashflow.application.repository;

import java.util.Optional;
import tw.waterball.cashflow.domain.entity.Actor;

public interface ActorRepository {

  Optional<Actor> findGameByActorName(String nickname);

  Actor save(Actor chineseChess);
}

package tw.waterball.cashflow.application.repository;

import tw.waterball.cashflow.domain.entity.Actor;

import java.util.Optional;

public interface ActorRepository {

  Optional<Actor> findGameByActorName(String nickname);

  Optional<Actor> findByActorId(String actorId);

  Actor save(Actor actor);
}

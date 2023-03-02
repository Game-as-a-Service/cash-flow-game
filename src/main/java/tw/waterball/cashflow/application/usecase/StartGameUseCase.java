package tw.waterball.cashflow.application.usecase;

import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.Career;

import java.util.HashMap;
import java.util.Map;

public class StartGameUseCase {
    private Map<String, Actor> actorMap = new HashMap<>(); //Map<nickname, Actor>

    public boolean start() {
        return !actorMap.isEmpty();
    }

    public void add(final Actor actor) {
        if (actorMap.containsKey(actor.getActorName())) {
            return;
        }

        actorMap.put(actor.getActorName(), actor);
    }
//
//    public exec(){
//
//        Actor actor1 = new Actor("name_1", Career.ENGINEER);
//        Actor actor2 = new Actor("name_2", Career.TEACHER);
//    }
}

package tw.waterball.cashflow.usecase;

import tw.waterball.cashflow.domain.entity.Actor;

import java.util.ArrayList;
import java.util.List;

public class StartGameUseCase {
    private List<Actor> actors = new ArrayList<>();
    boolean start() {
        return !actors.isEmpty() && actors.size() >= 2;
    }

    void add(Actor actor)
    {
        if(actors.contains(actor))
        {
            return;
        }

        actors.add(actor);
    }
}

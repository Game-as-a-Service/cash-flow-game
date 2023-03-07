package tw.waterball.cashflow.domain;

import lombok.Getter;
import tw.waterball.cashflow.domain.entity.Actor;

import java.util.Map;
import java.util.UUID;

import static java.lang.String.format;

public class CashFlowGame {
    @Getter
    private String id;
    @Getter
    private final Map<String, Actor> actors;

    public CashFlowGame(Map<String, Actor> actors) {
        this.id = UUID.randomUUID().toString();
        this.actors = actors;
    }

    public Actor getActor(String id) {
        if (actors.containsKey(id)) {
            return actors.get(id);
        }

        throw new IllegalArgumentException(format("Actor %s not found.", id));
    }
}

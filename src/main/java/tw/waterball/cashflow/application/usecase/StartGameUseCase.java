package tw.waterball.cashflow.application.usecase;

import tw.waterball.cashflow.application.usecase.util.FinancialStatementUtils;
import tw.waterball.cashflow.domain.entity.Actor;

import java.util.HashMap;
import java.util.Map;

public class StartGameUseCase {
    private Map<String, Actor> actorMap = new HashMap<>(); //Map<nickname, Actor>

    public boolean start() {
        if (actorMap.isEmpty()) {
            return false;
        }

        initializeFinancialStatements();
        return true;
    }

    public void add(final Actor actor) {
        if (actorMap.containsKey(actor.getActorName())) {
            return;
        }

        actorMap.put(actor.getActorName(), actor);
    }

    private void initializeFinancialStatements() {
        for (Actor actor : actorMap.values()) {
            actor.setFinancialStatement(FinancialStatementUtils.initialize(actor.getCareer()));
        }
    }
}

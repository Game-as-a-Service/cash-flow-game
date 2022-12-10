package tw.waterball.cashflow.usecase;

import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.usecase.util.FinancialStatementUtils;

import java.util.HashMap;
import java.util.Map;

public class StartGameUseCase {
    private Map<String, Actor> actorMap = new HashMap<>(); //Map<nickname, Actor>
    boolean start() {
        if(actorMap.isEmpty() || actorMap.size() < 2)
        {
            return false;
        }

        initializeFinancialStatements();
        return true;
    }

    void add(Actor actor)
    {
        if(actorMap.containsKey(actor.getNickname()))
        {
            return;
        }

        actorMap.put(actor.getNickname(), actor);
    }

    private void initializeFinancialStatements() {
        for(Actor actor : actorMap.values())
        {
            actor.setFinancialStatement(FinancialStatementUtils.initialize(actor.getCareer()));
        }
    }
}

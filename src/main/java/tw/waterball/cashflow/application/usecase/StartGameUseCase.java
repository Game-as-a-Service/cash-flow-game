package tw.waterball.cashflow.application.usecase;

import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.application.usecase.util.FinancialStatementUtils;

import java.util.HashMap;
import java.util.Map;

public class StartGameUseCase {
    private Map<String, Actor> actorMap = new HashMap<>(); //Map<nickname, Actor>
    public boolean start() {
        if(actorMap.isEmpty())
        {
            return false;
        }

        initializeFinancialStatements();
        return true;
    }

    public void add(Actor actor)
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
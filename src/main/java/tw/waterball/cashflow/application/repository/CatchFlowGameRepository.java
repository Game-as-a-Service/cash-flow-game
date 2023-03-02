package tw.waterball.cashflow.application.repository;

import tw.waterball.cashflow.domain.CatchFlowGame;

import java.util.Optional;

public interface CatchFlowGameRepository {
    Optional<CatchFlowGame> findById(String id);
}

package tw.waterball.cashflow.application.repository;

import tw.waterball.cashflow.domain.CashFlowGame;

import java.util.Optional;

public interface CashFlowGameRepository {
    Optional<CashFlowGame> findById(String id);
}

package tw.waterball.cashflow.springboot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tw.waterball.cashflow.application.repository.CatchFlowGameRepository;
import tw.waterball.cashflow.domain.CatchFlowGame;
import tw.waterball.cashflow.springboot.repository.dao.CashFlowGameDAO;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpringCashFlowGameRepository implements CatchFlowGameRepository {
    private final CashFlowGameDAO dao;


    @Override
    public Optional<CatchFlowGame> findById(String id) {
        // TODO
        return Optional.empty();
    }
}

package tw.waterball.cashflow.springboot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tw.waterball.cashflow.application.repository.CashFlowGameRepository;
import tw.waterball.cashflow.domain.CashFlowGame;
import tw.waterball.cashflow.springboot.repository.dao.CashFlowGameDAO;
import tw.waterball.cashflow.springboot.repository.data.CashFlowGameData;

import java.util.Optional;

import static tw.waterball.cashflow.springboot.repository.data.CashFlowGameData.toData;

@Repository
@RequiredArgsConstructor
public class SpringCashFlowGameRepository implements CashFlowGameRepository {
    private final CashFlowGameDAO dao;


    @Override
    public Optional<CashFlowGame> findById(String id) {
        // TODO
        return Optional.empty();
    }

    @Override
    public CashFlowGame save(CashFlowGame cashFlowGame) {
        CashFlowGameData data = toData(cashFlowGame);
        CashFlowGameData savedData = dao.save(data);
        return savedData.toDomain();
    }


}

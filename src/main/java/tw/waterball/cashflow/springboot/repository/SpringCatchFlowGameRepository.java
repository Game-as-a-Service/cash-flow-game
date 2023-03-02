package tw.waterball.cashflow.springboot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tw.waterball.cashflow.application.repository.CatchFlowGameRepository;
import tw.waterball.cashflow.domain.CatchFlowGame;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpringCatchFlowGameRepository implements CatchFlowGameRepository {

    @Override
    public Optional<CatchFlowGame> findById(String id) {
        // TODO
        return Optional.empty();
    }
}

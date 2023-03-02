package tw.waterball.cashflow.springboot.repository.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tw.waterball.cashflow.springboot.repository.data.CatchFlowGameData;

@Repository
public interface CatchFlowGameDAO extends MongoRepository<CatchFlowGameData, String> {}

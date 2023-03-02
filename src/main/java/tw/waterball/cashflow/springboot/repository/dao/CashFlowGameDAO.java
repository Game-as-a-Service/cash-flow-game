package tw.waterball.cashflow.springboot.repository.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tw.waterball.cashflow.springboot.repository.data.CashFlowGameData;

@Repository
public interface CashFlowGameDAO extends MongoRepository<CashFlowGameData, String> {}

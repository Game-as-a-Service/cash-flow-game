package tw.waterball.cashflow.springboot.repository.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.waterball.cashflow.domain.CashFlowGame;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashFlowGameData {
    private String id;
    private List<ActorData> actors;

    public static CashFlowGameData toData(CashFlowGame cashFlowGame) {
        List<ActorData> actors = cashFlowGame.getActors().values()
                                    .stream()
                                    .map(actor -> {
                                        ActorData actor2 = new ActorData();
                                        actor2.setDream(actor.getDream());
                                        actor2.setId(actor.getActorId());
                                        actor2.setCareer(actor.getCareer());
                                        return actor2;
                                    })
                                    .collect(Collectors.toList());

//        List<ActorData> actors = mapToList(cashFlowGame.getActors(), PlayerData::toData);
        return new CashFlowGameData(cashFlowGame.getId(), actors);
    }

    public CashFlowGame toDomain() {
        // TODO
        return  null;
    }
}

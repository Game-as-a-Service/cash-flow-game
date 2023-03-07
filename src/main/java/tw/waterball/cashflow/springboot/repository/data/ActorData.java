package tw.waterball.cashflow.springboot.repository.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.waterball.cashflow.domain.entity.Career;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorData {
    private String id;
    private String actorName;
    private String dream;
    private Career career;
}

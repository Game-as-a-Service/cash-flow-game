package tw.waterball.cashflow.springboot.controller;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tw.waterball.cashflow.application.repository.CashFlowGameRepository;
import tw.waterball.cashflow.domain.CashFlowGame;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.Career;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CashFlowGameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CashFlowGameRepository gameRepository;

    @Test
    void 取得角色資訊() throws Exception {
        // given
        String actorA = "actorA";
        Actor actor1 = new Actor(actorA, Career.ENGINEER);
        CashFlowGame cashFlowGame = new CashFlowGame(Map.of(actorA, actor1));
        String roomId = cashFlowGame.getId();
        gameRepository.save(cashFlowGame);


        mockMvc.perform(get("/api/game/actor").contentType(APPLICATION_JSON)
                                              .content("{\"roomId\":\"" + roomId + "\"," + "\"actor\":\"" + actorA +
                                                       "\"}"))
               .andExpect(status().isOk());
    }

    /**
     * 玩家皆加入遊戲且準備完成，主持人啟動遊戲，
     * 由系統隨機選擇玩家職業
     *
     * @throws Exception
     */
    @Test
    void 啟動遊戲() throws Exception {
        String roomId = "roomId1";
        String actorA = "actorA";
        String actorB = "actorB";

        mockMvc.perform(post("/api/game/start").contentType(MediaType.APPLICATION_JSON)
                                               .content("{\"roomId\":\"" + roomId + "\",\"actor\":[\"" + actorA +
                                                        "\",\"" + actorB + "\"]}"))
               .andExpect(status().isNoContent());


        CashFlowGame actualGame = findGameById(roomId);

        Actor actualA = actualGame.getActor(actorA);
        Assertions.assertNotNull(actualA.getCareer());

        Actor actualB = actualGame.getActor(actorB);
        Assertions.assertNotNull(actualB.getCareer());
    }

    private CashFlowGame findGameById(String roomId) {
        // 從 repo 查出 game
        return gameRepository.findById(roomId).orElseThrow();
    }
}

package tw.waterball.cashflow.springboot.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import tw.waterball.cashflow.application.repository.CatchFlowGameRepository;
import tw.waterball.cashflow.domain.CatchFlowGame;
import tw.waterball.cashflow.domain.entity.Actor;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CatchFlowGameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CatchFlowGameRepository gameRepository;

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

        mockMvc.perform(post("/api/game/start").contentType(APPLICATION_JSON)
                                               .content("{\"roomId\":\"" + roomId + "\",\"actor\":[\"" + actorA +
                                                        "\",\"" + actorB + "\"]}"))
               .andExpect(status().isNoContent());


        CatchFlowGame actualGame = findGameById(roomId);

        Actor actualA = actualGame.getActor(actorA);
        Assertions.assertNotNull(actualA.getCareer());

        Actor actualB = actualGame.getActor(actorB);
        Assertions.assertNotNull(actualB.getCareer());
    }

    private CatchFlowGame findGameById(String roomId) {
        // 從 repo 查出 game
        return gameRepository.findById(roomId).orElseThrow();
    }
}

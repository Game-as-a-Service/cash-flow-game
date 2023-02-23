package tw.waterball.cashflow.springboot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CatchFlowGameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 啟動遊戲() throws Exception {

        mockMvc.perform(post("/api/game/start").contentType(APPLICATION_JSON).
                       content("{\"roomId\":\"roomId1\",\"actor\":[\"actorId_A\",\"actorId_B\"]}"))
               .andExpect(status().isOk());

    }
}

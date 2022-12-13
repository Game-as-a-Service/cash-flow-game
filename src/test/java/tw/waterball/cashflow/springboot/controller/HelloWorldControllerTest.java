package tw.waterball.cashflow.springboot.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tw.waterball.cashflow.springboot.CashFlowApplication;

@SpringBootTest(classes = CashFlowApplication.class)
@AutoConfigureMockMvc
class HelloWorldControllerTest {

  @Autowired
  MockMvc mockMvc; // 幫我送 HTTP request 到我的 Controller

  @Test
  void testHello() throws Exception {
    // when
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
//            .contentType(MediaType.APPLICATION_JSON)
        .andExpect(status().isOk()).andReturn();

    // then
    String result = mvcResult.getResponse().getContentAsString();
    Assertions.assertEquals("world", result);
  }

}
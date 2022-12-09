package tw.waterball.cashflow.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.waterball.cashflow.CashFlowApplication;
import org.springframework.boot.test.context.SpringBootTest;
@RestController

@SpringBootTest(classes = CashFlowApplication.class)
@AutoConfigureMockMvc
public class HelloWorldController
{
    @GetMapping(path = "hello", produces = {MediaType.TEXT_PLAIN_VALUE})
    public String hello()
    {
        return "world";
    }
}

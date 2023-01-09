package tw.waterball.cashflow.springboot.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping(path = "hello", produces = {MediaType.TEXT_PLAIN_VALUE})
    public String hello() {
        return "world";
    }
}

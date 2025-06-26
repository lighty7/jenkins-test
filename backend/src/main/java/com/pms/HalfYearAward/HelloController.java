package com.pms.HalfYearAward;

import org.springframework.web.bind.annotation.Get  Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // Base path for your API endpoints
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot Backend!";
    }
}
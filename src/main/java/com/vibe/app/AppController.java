package com.vibe.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AppController {

    @RequestMapping("/greet")
    public String greet() {
        return "hello world";
    }
}

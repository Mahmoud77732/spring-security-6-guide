package com.hegazy.ssecuritypart1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WelcomeController {
    
    @GetMapping("/welcome")
    public String sayWelcome(){
        return "Hello from Welcome Controller!";
    }
    
}

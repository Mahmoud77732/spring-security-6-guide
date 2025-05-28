package com.hegazy.ssecurity.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/welcome")
public class WelcomeController {
    
    @GetMapping("") 
    public String sayWelcome(@RequestParam String userName){
        return "Welcome " + userName + "!";
    }
    
}

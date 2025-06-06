package com.hegazy.ssecurity.user_in_memory_3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @GetMapping("/myCards")
    public String getCardsDetails(){
        return "Here are the cards details from the DB";
    }

}

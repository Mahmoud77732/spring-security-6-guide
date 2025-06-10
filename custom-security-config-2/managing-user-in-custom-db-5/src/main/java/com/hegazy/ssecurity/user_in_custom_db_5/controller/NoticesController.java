package com.hegazy.ssecurity.user_in_custom_db_5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {

    @GetMapping("/notices")
    public String getNotices(){
        return "Here are the notices details from the DB";
    }

}

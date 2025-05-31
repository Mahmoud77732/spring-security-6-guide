/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hegazy.ssecurity.security_beans_2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mahmoud
 */
@RestController
@RequestMapping("api/entity5")
public class Entity5Controller {
    
    @GetMapping("") 
    public String sayEntity5(@RequestParam String userName){
        return "Entity5: Welcome " + userName + "!";
    }
    
}

package com.hegazy.ssecurityOAuth2.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SecureController {

    @GetMapping("/secure")
    public String securePage(Authentication authentication) {
        if(authentication instanceof UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken){
            System.out.println(usernamePasswordAuthenticationToken);
            // System.out.println("Username: " + usernamePasswordAuthenticationToken.getName());
        }
        else if(authentication instanceof OAuth2AuthenticationToken oAuth2AuthenticationToken){
            System.out.println(oAuth2AuthenticationToken);

        }
        return "secure.html";
    }
    

}

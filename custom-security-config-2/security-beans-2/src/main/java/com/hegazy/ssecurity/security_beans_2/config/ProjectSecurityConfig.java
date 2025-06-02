/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hegazy.ssecurity.security_beans_2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

/**
 *
 * @author mahmoud
 */
@Configuration
public class ProjectSecurityConfig {

    /*
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // requests.anyRequest().permitAll(): no endpoints are secured
        // requests.anyRequest().denyAll(): deny even authenticated users
        // requests.anyRequest().authenticated(): user must enter {name & password}
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }
    */
    
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // "/error" : it is free endpoint, but we want it not secured so we can redirected to it
        // If you don't mention these anywhere,
        // then by default Spring Security framework is always going to throw 403 error.
        // Since we are trying to learn Spring Security framework and we always want to know
        // what is the runtime exception happen behind the scenes,
        // I have marked these error under the permitAll()
        http.authorizeHttpRequests(
                (requests) ->
                        requests.requestMatchers(
                                "/myCards", "/myBalance", "/myAccount", "/myLoans")
                                .authenticated()
                                .requestMatchers(
                                        "/contact", "/notices", "/error")
                                .permitAll()
        );

        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        // http.formLogin().disable(); // is deprecated
        //http.formLogin(hlp -> hlp.disable());
        //http.httpBasic(hbc -> hbc.disable());
        return http.build();
    }

}

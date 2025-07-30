package com.hegazy.ssecuritypart2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
    
    /*
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                (requests) -> 
                        // requests.anyRequest().permitAll()
                        // requests.anyRequest().denyAll()
                        requests.anyRequest().authenticated()
        );
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }
    */
    
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                (requests) -> requests
                        .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                        .requestMatchers("/notices", "/contact", "/myLoans", "/myCards", "/error").permitAll()
        );
         http.formLogin(withDefaults());
         http.httpBasic(withDefaults());
        // http.formLogin(login -> login.disable());
        // http.httpBasic(basicConfig -> basicConfig.disable());
        return http.build();
    }
    
}

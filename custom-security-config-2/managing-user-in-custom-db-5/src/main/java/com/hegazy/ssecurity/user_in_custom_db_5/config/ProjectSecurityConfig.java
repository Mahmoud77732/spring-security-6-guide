/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hegazy.ssecurity.user_in_custom_db_5.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

/**
 *
 * @author mahmoud
 */
@Configuration
public class ProjectSecurityConfig {

    
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                (requests) ->
                        requests.requestMatchers(
                                "/myCards", "/myBalance", "/myAccount", "/myLoans")
                                .authenticated()
                                .requestMatchers(
                                        "/contact", "/notices", "/error", "/register")
                                .permitAll()
        );

        http.csrf(csrf -> csrf.disable());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }
    
    /*
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        // Admin-1@12345, User_1@12345
        return new JdbcUserDetailsManager(dataSource);
    }
    */
    
    // if u use "bcrypt" + userDetailsService with default settings: u don't need to implement this bean
    @Bean
    public PasswordEncoder passwordEncoder(){
        // return new BCryptPasswordEncoder();

        // new version of spring security
        // by default it use "BCryptPasswordEncoder" / "bcrypt"
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * From Spring Security 6.3 version
     * @return
     */
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
    

}

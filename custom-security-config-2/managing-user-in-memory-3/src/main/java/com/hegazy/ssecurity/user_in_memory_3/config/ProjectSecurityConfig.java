/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hegazy.ssecurity.user_in_memory_3.config;

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
                                        "/contact", "/notices", "/error")
                                .permitAll()
        );

        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }
    
    // - we used already "passwordEncoder" bean to encode our password
    // -so used "12345" without {noop} with it to be encoded
    // - we now want to hide "12345" from the code we write
    // - so we will use site to encrypt our "12345"
    // - then to make spring know that out new encrypted-value not a plain-text
    // -    we will prefix the encrypted value in password() with {bcrypt}
    @Bean
    public UserDetailsService userDetailsService(){
        // - using "pass" -> error
        // - using "{noop}pass" without "@Bean PasswordEncoder" -> plain-Text
        // - using "pass" + "@Bean PasswordEncoder" -> pass: stored in a bcrypt hash format
        // UserDetails user = User.withUsername("user").password("12345").authorities("read").build();
        // - 12345 - only to not write the password as a plain text in the config class
        // UserDetails user = User.withUsername("user").password("{bcrypt}$2a$12$FQbf7i6UosgYd2uylOhxYekgEt1xUg7TnUGX1MEgtHrm3jp448zie").authorities("read").build();
        // - after using "CompromisedPasswordChecker" = User-1@12345
        UserDetails user = User.withUsername("user").password("{bcrypt}$2a$12$mixQFmF.AyrHoD1T0ahWRuOnVX/D7m2Ue2dKpWx6WrondGji1/1Oe").authorities("read").build();
        // - Admin-1@12345
        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$t5Jpc0mATH3FRoOWlsuQeOB3Rrf9T0UozKTRX1WOsjD9CRXczKhN2").authorities("admin").build();
        return new InMemoryUserDetailsManager(user, admin);
    }
    
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

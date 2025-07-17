package com.hegazy.ssecuritypart3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
    
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                (requests) -> requests
                        .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                        .requestMatchers("/notices", "/contact", "/myLoans", "/myCards", "/error").permitAll()
        );
         http.formLogin(withDefaults());
         http.httpBasic(withDefaults());
        return http.build();
    }
    
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User
                .withUsername("user")
                .password("{noop}user123")
                .authorities("read")
                .build();
        UserDetails admin = User
                .withUsername("admin")
                .password("{noop}admin123")
                .authorities("admin")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
    
}

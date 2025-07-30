package com.hegazy.ssecuritypart3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

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
    
    
    /*
    * UserDetailsService _loadUserByUsername(String username)_
        |_ UserDetailsManager _createUser(UserDetails user)_, _updateUser(...)_, ...
            |_ InMemoryUserDetailsManager
            |_ JdbcUserDetailsmanager
            |_ LdapUserDetailsmanager
    * All the above uses an interface UserDetails & its implementation which provides core user info
    */
    // site used to generate hash: https://bcrypt-generator.com/
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User
                .withUsername("user")
                .password("{bcrypt}$2a$12$biRq6Cg6M0vRBuyBMT09be3MHMtoCI5wC.JXkgP6O5Fk/VSoVwqee") // User_12345@@
                .authorities("read")
                .build();
        UserDetails admin = User
                .withUsername("admin")
                .password("{bcrypt}$2a$12$mVArnepu3CJltRp3Ly.VCevTZHOoaYQ7zfqBGAqlhrSE8xjzGmZwq") // Admin_12345@@
                .authorities("admin")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        // encodingId is "bcrypt" by default
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    // force user to enter strong password
    // no for: user123
    // yes for: User_12345@@, Admin_12345@@
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
    
}

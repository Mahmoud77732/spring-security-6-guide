package com.hegazy.ssecuritypart14.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import static org.springframework.security.config.Customizer.withDefaults;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

import com.hegazy.ssecuritypart14.exceptionhandling.CustomAccessDeniedHandler;
import com.hegazy.ssecuritypart14.exceptionhandling.CustomBasicAuthenticationEntryPoint;

@Configuration
@Profile("prod")
public class ProjectSecurityProdConfig {
    
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        http.sessionManagement(smc -> smc.invalidSessionUrl("/invalidSession").maximumSessions(1));
        http.authorizeHttpRequests(
                (requests) -> requests
                        .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                        .requestMatchers("/notices", "/contact", "/myLoans", "/myCards", "/register", "/error", "/invalidSession").permitAll()
        );
        http.csrf(csrf -> csrf.disable());
        http.formLogin(withDefaults());
        http.httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        // force HTTPS protocol
        http.redirectToHttps(httpsConfig -> httpsConfig.requestMatchers(AnyRequestMatcher.INSTANCE));
        // global config, the difference between it and handlnig it through the "http.basic"
        // http.exceptionHandling(exConfig -> exConfig.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        // http.exceptionHandling(exConfig -> exConfig.accessDeniedHandler(new CustomAccessDeniedHandler()).accessDeniedPage("/denied"));
        http.exceptionHandling(exConfig -> exConfig.accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
    }

    // @Bean
    // UserDetailsService userDetailsService(DataSource dataSource){
    //     return new JdbcUserDetailsManager(dataSource);
    // }
    
    @Bean
    PasswordEncoder passwordEncoder(){
        // encodingId is "bcrypt" by default
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CompromisedPasswordChecker compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
    
}

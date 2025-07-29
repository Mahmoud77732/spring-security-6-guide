package com.hegazy.ssecuritypart19.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.hegazy.ssecuritypart19.exceptionhandling.CustomBasicAuthenticationEntryPoint;
import com.hegazy.ssecuritypart19.filter.AuthoritiesLoggingAfterFilter;
import com.hegazy.ssecuritypart19.filter.AuthoritiesLoggingAtFilter;
import com.hegazy.ssecuritypart19.filter.CsrfCookieFilter;
import com.hegazy.ssecuritypart19.filter.RequestValidationBeforeFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@Profile("!prod")
public class ProjectSecurityConfig {
    
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        
        http.securityContext(contextConfig -> contextConfig.requireExplicitSave(false));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        // http.csrf(csrf -> csrf.disable());
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        csrfTokenRequestAttributeHandler.setCsrfRequestAttributeName("_csrf");
        
        http.csrf(csrf -> csrf
            .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .ignoringRequestMatchers("/contact", "/register")
        );

        http.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class);
        http.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);
        http.addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class);
        http.addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class);

        http.authorizeHttpRequests(
                (requests) -> requests
                        // .requestMatchers("/myAccount").hasAuthority("VIEWACCOUNT")
                        // .requestMatchers("/myBalance").hasAnyAuthority("VIEWBALANCE", "VIEWACCOUNT")
                        // .requestMatchers("/myLoans").hasAuthority("VIEWLOANS")
                        // .requestMatchers("/myCards").hasAuthority("VIEWCARDS")
                        .requestMatchers("/myAccount").hasRole("USER") // ROLE_USER -> USER
                        .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/myLoans").hasRole("USER")
                        .requestMatchers("/myCards").hasRole("USER")
                        .requestMatchers("/user").authenticated()
                        .requestMatchers(
                            "/", "/home", "/notices", "/contact", "/register", 
                            "/error", "/login/**", "/logout", "/assets/**").permitAll()
        );
        
        http.formLogin(form -> form
            .loginPage("/login")
            .defaultSuccessUrl("/")
        );
        
        http.logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout")
            .permitAll()
        );
        
        // http.httpBasic(withDefaults());
        http.httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        
        // allow HTTP not HTTPS only
        http.redirectToHttps(httpsConfig -> httpsConfig.disable());

        //cors.addMapping("/api/**").allowedOrigins("http://localhost:4200");
        //cors.addMapping("/api/**").allowedOrigins(allowedOrigins);
        // cors.addMapping(config.getBasePath()+ "/**").allowedOrigins(allowedOrigins);
        // props; "allowed.origins=http://localhost:4200"
        // on Controller: "@CrossOrigin("http://localhost:4200")"

        http.cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                config.setAllowedMethods(Collections.singletonList("*")); // http methods
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setMaxAge(3600L);
                return config;
            }
        }));

        return http.build();
    }
    
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

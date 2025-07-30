package com.hegazy.ssecuritypart20.config;

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
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.hegazy.ssecuritypart20.exceptionhandling.CustomAccessDeniedHandler;
import com.hegazy.ssecuritypart20.exceptionhandling.CustomBasicAuthenticationEntryPoint;
import com.hegazy.ssecuritypart20.filter.CsrfCookieFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@Profile("prod")
public class ProjectSecurityProdConfig {
    
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
        http.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);

        http.authorizeHttpRequests(
                (requests) -> requests
                        .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards", "/user").authenticated()
                        .requestMatchers("/", "/home", "/notices", "/contact", "/register", "/error", "/login/**", "/logout",
                            "/assets/**").permitAll()
        );
        
        http.formLogin(form -> form
            .loginPage("/login")
            .defaultSuccessUrl("/", true)
            .permitAll()
        );
        
        
        // global config, the difference between it and handlnig it through the "http.basic"
        // http.exceptionHandling(exConfig -> exConfig.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        // http.exceptionHandling(exConfig -> exConfig.accessDeniedHandler(new CustomAccessDeniedHandler()).accessDeniedPage("/denied"));
        http.exceptionHandling(exConfig -> exConfig.accessDeniedHandler(new CustomAccessDeniedHandler()));
        
        http.logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout")
            .permitAll());
        
        http.httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        // force HTTPS protocol
        http.redirectToHttps(httpsConfig -> httpsConfig.requestMatchers(AnyRequestMatcher.INSTANCE));
        
        //cors.addMapping("/api/**").allowedOrigins("http://localhost:4200");
        //cors.addMapping("/api/**").allowedOrigins(allowedOrigins);
        // cors.addMapping(config.getBasePath()+ "/**").allowedOrigins(allowedOrigins);
        // props; "allowed.origins=http://localhost:4200"
        // on Controller: "@CrossOrigin("http://localhost:4200")"

        http.cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("https://localhost:4200")); //! Use HTTPS for production
                config.setAllowedMethods(Collections.singletonList("*")); // http methods
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setMaxAge(3600L);
                return config;
            }
        }));

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

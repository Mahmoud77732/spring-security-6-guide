package com.hegazy.ssecuritypart20.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.hegazy.ssecuritypart20.exceptionhandling.CustomBasicAuthenticationEntryPoint;
import com.hegazy.ssecuritypart20.filter.AuthoritiesLoggingAfterFilter;
import com.hegazy.ssecuritypart20.filter.AuthoritiesLoggingAtFilter;
import com.hegazy.ssecuritypart20.filter.CsrfCookieFilter;
import com.hegazy.ssecuritypart20.filter.JWTTokenGeneratorFilter;
import com.hegazy.ssecuritypart20.filter.JWTTokenValidatorFilter;
import com.hegazy.ssecuritypart20.filter.RequestValidationBeforeFilter;

import java.util.Arrays;
import jakarta.servlet.http.HttpServletRequest;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("!prod")
public class ProjectSecurityConfig {
    
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        
        // http.securityContext(contextConfig -> contextConfig.requireExplicitSave(false));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // http.csrf(csrf -> csrf.disable());
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        csrfTokenRequestAttributeHandler.setCsrfRequestAttributeName("_csrf");
        
        http.csrf(csrf -> csrf
            .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .ignoringRequestMatchers("/contact", "/register", "/apiLogin")
        );

        http.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class);
        http.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);
        http.addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class);
        http.addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class);
        http.addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class);
        http.addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class);

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
                            "/error", "/login/**", "/logout", "/assets/**", "/apiLogin").permitAll()
        );
        
        // http.formLogin(form -> form
        //     .loginPage("/login")
        //     .defaultSuccessUrl("/")
        // );
        http.formLogin(withDefaults());
        
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
                config.setExposedHeaders(Arrays.asList("Authorization"));
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

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        MyUsernamePwdAuthenticationProvider authenticationProvider =
                new MyUsernamePwdAuthenticationProvider(userDetailsService, passwordEncoder);
        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return  providerManager;
    }
    
}

package com.hegazy.ssecurityOAuth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/secure").authenticated()
                .anyRequest().permitAll());
        http.formLogin(Customizer.withDefaults());
        // http.oauth2Login(Customizer.withDefaults());
        http.oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("/secure", true));
        return http.build();
    }

    /*
    * Each ClientRegistration represents a single OAuth2 client 
    * (e.g., Google, GitHub, Okta, etc.), containing client ID, secret, 
    * scopes, authorization URI, token URI, etc
    *
    * Info stored in memory
    *
    * Discover "CommonOAuth2Provider" contains {GOOGLE, GITHUB, OKTA, ...etc}
    * and each client like GOOGLE includes its' info {scope, authorizationUri, tokenUri, ussuerUri, ...etc}
    */
    /*
    @Bean
    ClientRegistrationRepository clientRegistrationRepository(){
        ClientRegistration github = githubClientRegistration();
        ClientRegistration facebook = facebookClientRegistration();
        return new InMemoryClientRegistrationRepository(github, facebook);
    }
    */

    /*
    private ClientRegistration githubClientRegistration(){
        return CommonOAuth2Provider.GITHUB
            .getBuilder("github")
            .clientId("Ov23liOUPZnJim5TDE8w")
            .clientSecret("83c1d907516bc3096dfb5d85967cff3eddda16ee")
            .build();
    }
    */
    
    /*
    private ClientRegistration facebookClientRegistration(){
        return CommonOAuth2Provider.FACEBOOK
            .getBuilder("facebook")
            .clientId("690404263961116")
            .clientSecret("2ad005ba2617f03be1e5253c012c7102")
            .build();
    }
    */

}

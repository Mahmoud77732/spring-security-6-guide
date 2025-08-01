package com.hegazy.ssecuritypart21.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hegazy.ssecuritypart21.constants.ApplicationConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {

    /*
    * This filter generates a JWT token for authenticated users.
    * It retrieves the authentication from the security context,
    * and if the user is authenticated, it creates a JWT token with the user's details.
    * The token is then added to the response header.
    * The secret key for signing the JWT is retrieved from the environment properties,
    * with a default value provided in the ApplicationConstants class.
    * The token includes the issuer, subject, username, authorities, issued at time,
    * and expiration time.
    * The filter should not apply to the "/login" endpoint, as it is meant for
    * generating tokens for authenticated requests only.
    */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException 
    {
        // Logic to generate JWT token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()) {
            Environment env = getEnvironment();
            if(env != null){
                String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY, ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                String jwt = Jwts.builder()
                        .issuer("Hegazy App")
                        .subject("JWT Token")
                        .claim("username", authentication.getName())
                        .claim("authorities", authentication.getAuthorities().stream().map(
                                GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                        .issuedAt(new Date())
                        .expiration(new Date((new Date()).getTime() + 30000000))
                        .signWith(secretKey).compact();
                response.setHeader(ApplicationConstants.JWT_HEADER, jwt);
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/login");
    }

}

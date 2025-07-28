package com.hegazy.ssecuritypart16.filter;

import java.io.IOException;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CsrfCookieFilter  extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain) 
        throws ServletException, IOException 
    {
        // Custom logic for CSRF cookie handling can be added here
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrfToken != null) {
            // Set the CSRF token in a cookie
            response.setHeader("X-CSRF-TOKEN", csrfToken.getToken());
            response.addCookie(new Cookie("XSRF-TOKEN", csrfToken.getToken()));
        } else {
            // If no CSRF token is present, you might want to handle it accordingly
            response.addCookie(new Cookie("XSRF-TOKEN", ""));
        }
        filterChain.doFilter(request, response);
    }
    
    // Additional methods for CSRF cookie management can be added here

}

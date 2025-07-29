package com.hegazy.ssecuritypart19.filter;

import java.io.IOException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class AuthoritiesLoggingAfterFilter implements Filter{

    private static final Logger logger = LoggerFactory.getLogger(AuthoritiesLoggingAfterFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        // Log the authorities after the request has been processed
        HttpServletRequest req = (HttpServletRequest) request;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            logger.info("Request URL: {}, Method: {}, User: {} is successfully authenticated and has the authorities: {}",
                    req.getRequestURL(), req.getMethod(), authentication.getName(), authorities);
        } else {
            logger.info("Request URL: {}, Method: {}, No authenticated user found for this request.",
                    req.getRequestURL(), req.getMethod());
        }
        // Continue the filter chain
        chain.doFilter(request, response);
    }

}

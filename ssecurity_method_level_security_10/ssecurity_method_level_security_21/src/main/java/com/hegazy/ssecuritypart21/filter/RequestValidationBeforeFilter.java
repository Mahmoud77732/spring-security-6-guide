package com.hegazy.ssecuritypart21.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RequestValidationBeforeFilter implements Filter{

    // This filter can be used to validate requests before they reach the security filters
    // You can implement your validation logic here, such as checking headers, parameters, etc.
    
    // Example: Override doFilter method to add custom validation logic
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        if(header != null){
            header = header.trim();
            if(StringUtils.startsWithIgnoreCase(header, "Basic")){
                byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
                byte[] decoded;
                try{
                    decoded = Base64.getDecoder().decode(base64Token);
                    String token = new String(decoded, StandardCharsets.UTF_8);
                    int delim = token.indexOf(":");
                    if(delim == -1){
                        throw new BadCredentialsException("Invalid basic authentication token");
                    }
                    String email = token.substring(0, delim).trim();
                    if(email.toLowerCase().contains("test")){
                        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        return;
                    }
                }
                catch (IllegalArgumentException e) {
                    throw new BadCredentialsException("Invalid basic authentication token", e);
                }
            }
        }
        chain.doFilter(request, response);
    }

}

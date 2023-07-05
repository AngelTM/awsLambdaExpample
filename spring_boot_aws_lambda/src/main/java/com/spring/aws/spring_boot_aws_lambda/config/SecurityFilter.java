package com.spring.aws.spring_boot_aws_lambda.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class SecurityFilter implements Filter{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String token = httpServletRequest.getHeader("token");

        if(token == null || token.isEmpty() || !token.equalsIgnoreCase("ABC123")){

            ObjectMapper mapper = new ObjectMapper();

            ObjectNode error = mapper.createObjectNode();
            error.put("Error", "Token invalid or empty");

            httpServletResponse.setStatus(401);
            httpServletResponse.setContentType("application/json");

            httpServletResponse.getOutputStream().write(mapper.writeValueAsBytes(error));
            httpServletResponse.getOutputStream().flush();
        } else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
    
}

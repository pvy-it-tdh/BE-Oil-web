package com.loinguyen1905.realestate.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loinguyen1905.realestate.model.response.RestResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
  
    private final AuthenticationEntryPoint delegate = new BearerTokenAuthenticationEntryPoint(); 

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void commence(
        HttpServletRequest request, HttpServletResponse response, AuthenticationException authException
    ) throws IOException, ServletException {
        delegate.commence(request, response, authException);
        response.setContentType("application/json;charset=UTF-8");

        RestResponse<Object> res = RestResponse
            .builder()
            .statusCode(HttpStatus.UNAUTHORIZED.value())
            .message("Authentication failed please check your token :3")
            .error(
                Optional.ofNullable(authException.getCause())
                    .map(Throwable::getMessage)
                    .orElse(authException.getMessage())
            )
            .build();

        mapper.writeValue(response.getWriter(), res);
    }

}
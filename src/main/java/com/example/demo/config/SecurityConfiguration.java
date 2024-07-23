package com.example.demo.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
        HttpSecurity http,
        CustomAuthenticationEntryPoint customAuthenticationEntryPoint
    ) throws Exception {
        http
            .csrf(c -> c.disable())
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests(
                authz -> authz
                    .requestMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated()
            )
            .oauth2ResourceServer(
                (oauth2) -> oauth2
                    .jwt(Customizer.withDefaults())
                    .authenticationEntryPoint(customAuthenticationEntryPoint) // Override customize invalid token in response
            )
            // .exceptionHandling(
            //     (exceptions) -> exceptions
            //         .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint()) // 401
            //         .accessDeniedHandler(new BearerTokenAccessDeniedHandler()) // 403
            // )
            .formLogin(f -> f.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Model working with security to Restful APIs web service the stateless is default

        return http.build();
    }
}
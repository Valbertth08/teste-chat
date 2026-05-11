package com.web_socket.sisssp.clients.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Configuration
public class UsuarioClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String token = "Bearer ";
                if (authentication != null) {
                    JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
                    token = token + jwtAuthenticationToken.getToken().getTokenValue();
                }
                template.header("Authorization", token);
            }
        };
    }
}

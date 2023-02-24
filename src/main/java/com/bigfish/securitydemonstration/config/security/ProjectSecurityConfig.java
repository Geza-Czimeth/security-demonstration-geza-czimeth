package com.bigfish.securitydemonstration.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().requestMatchers("/api/v1/users").authenticated().requestMatchers("/api/v1/nonAuthenticated/users").permitAll()
                .requestMatchers("/**").denyAll();
        http.formLogin();
        http.httpBasic();
        return http.build();
    }
}

package com.bigfish.securitydemonstration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().requestMatchers("/api/v1/orders").authenticated().requestMatchers("/api/v1/nonAuthenticated/orders").permitAll();
        http.formLogin();
        http.httpBasic();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("admin").build();
        UserDetails user1 = User.withDefaultPasswordEncoder().username("user1").password("user1").roles("user1").build();
        return new InMemoryUserDetailsManager(admin, user1);
    }
}
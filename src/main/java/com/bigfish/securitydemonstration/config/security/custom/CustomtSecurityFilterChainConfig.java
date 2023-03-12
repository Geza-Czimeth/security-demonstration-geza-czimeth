package com.bigfish.securitydemonstration.config.security.custom;

import com.bigfish.securitydemonstration.filter.AuthoritiesLoggingAfterFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.http.HttpMethod.POST;


@Configuration
@Profile("prod")
public class CustomtSecurityFilterChainConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers("/api/v1/users","/api/v2/users").hasAuthority("view")
                .requestMatchers("/api/v1/nonAuthenticated/users").permitAll()
                // Unauthenticated post request setup, for demonstration purpose only
                .requestMatchers(POST, "/api/v1/register").permitAll()
                .requestMatchers("/**").denyAll();
        http.addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class);
        http.formLogin();
        http.httpBasic();
        http.csrf().disable();
        return http.build();
    }
}

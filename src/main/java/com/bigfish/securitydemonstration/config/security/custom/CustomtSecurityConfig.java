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
@Profile("prod-custom-userdetailsservice")
public class CustomtSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers("/api/v1/users","/api/v2/users").hasAuthority("user")
                .requestMatchers("/api/v1/nonAuthenticated/users").permitAll()
                // Unauthenticated post request setup, not for production usage!
                .requestMatchers(POST, "/api/v1/register").permitAll()
                .requestMatchers("/**").denyAll();
        http.addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class);
        http.formLogin();
        http.httpBasic();
        // For the post request to work(registering users in this case, this must be added otherwise we get error
        // message 'Invalid CSRF token found for http://localhost:8080/api/v1/register'
        http.csrf().disable();
        return http.build();
    }
}

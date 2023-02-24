package com.bigfish.securitydemonstration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@Profile("dev")
public class InMemoryUserAuthenticationConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("admin").build();
        UserDetails user1 = User.withDefaultPasswordEncoder().username("user1").password("user1").roles("user1").build();
        return new InMemoryUserDetailsManager(admin, user1);
    }
}

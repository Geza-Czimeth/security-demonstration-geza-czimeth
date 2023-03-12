package com.bigfish.securitydemonstration.config.security.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@Profile("test")
public class JdbcBasedUserDetailsManager {

    @Bean
    public UserDetailsService userDetailsServiceDatabase(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder noOpPasswordEncoder() {
        // Intentionally plain text for testing purposes
        return NoOpPasswordEncoder.getInstance();
    }
}

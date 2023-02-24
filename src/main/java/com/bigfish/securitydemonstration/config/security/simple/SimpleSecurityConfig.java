package com.bigfish.securitydemonstration.config.security.simple;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;


@Profile("dev-simple-security")
@Configuration
@PropertySource("classpath:simple-security.properties")
public class SimpleSecurityConfig {

}

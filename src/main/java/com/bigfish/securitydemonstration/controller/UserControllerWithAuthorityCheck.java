package com.bigfish.securitydemonstration.controller;

import com.bigfish.securitydemonstration.model.Customer;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bigfish.securitydemonstration.model.Constants.TEST_CUSTOMER;

@RestController
@RequestMapping("/api/v2")
@Profile("prod")
public class UserControllerWithAuthorityCheck {

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('view')")
    public ResponseEntity<Customer> load() {
        return ResponseEntity.ok(TEST_CUSTOMER);
    }
}

package com.bigfish.securitydemonstration.controller;

import com.bigfish.securitydemonstration.model.Constants;
import com.bigfish.securitydemonstration.model.Customer;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
@Profile("prod-custom-userdetailsservice")
public class UserControllerWithRoleCheck {

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Customer> load(){
        return ResponseEntity.ok(Constants.TEST_CUSTOMER);
    }
}

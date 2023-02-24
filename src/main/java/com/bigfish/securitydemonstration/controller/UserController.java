package com.bigfish.securitydemonstration.controller;

import com.bigfish.securitydemonstration.model.Constants;
import com.bigfish.securitydemonstration.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @GetMapping("/users")
    public ResponseEntity<Customer> load(){
        return ResponseEntity.ok(Constants.TEST_CUSTOMER);
    }

    @GetMapping("/nonAuthenticated/users")
    public ResponseEntity<Customer> loadWithoutAuthentication(){
        return ResponseEntity.ok(Constants.TEST_CUSTOMER);
    }
}

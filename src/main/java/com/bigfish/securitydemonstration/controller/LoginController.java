package com.bigfish.securitydemonstration.controller;

import com.bigfish.securitydemonstration.business.UserService;
import com.bigfish.securitydemonstration.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class LoginController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        ResponseEntity responseEntity = null;
        if (userService.registerUser(customer)) {
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body("Given user details are successfully created");
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error!");
        }
        return responseEntity;
    }
}

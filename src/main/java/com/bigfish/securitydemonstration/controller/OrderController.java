package com.bigfish.securitydemonstration.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @GetMapping("/orders")
    public ResponseEntity<String> load(){
        return ResponseEntity.ok("TestOrder");
    }

    @GetMapping("/nonAuthenticated/orders")
    public ResponseEntity<String> loadWithoutAuthentication(){
        return ResponseEntity.ok("TestOrder");
    }
}

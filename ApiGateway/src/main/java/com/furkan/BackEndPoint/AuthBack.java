package com.furkan.BackEndPoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class AuthBack {
    @GetMapping("/authfall")
    public ResponseEntity<String> authfall(){
        return ResponseEntity.ok("auth servis kapali");
    }
}

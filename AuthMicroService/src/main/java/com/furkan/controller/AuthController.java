package com.furkan.controller;


import com.furkan.dto.request.AuthLoginDto;
import com.furkan.dto.request.AuthSaveDto;
import com.furkan.exception.AuthException;
import com.furkan.exception.EErrorType;
import com.furkan.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth-save")
    public ResponseEntity<String> createAuth(@RequestBody AuthSaveDto dto){
        if (dto.getPassword()!=dto.getRepassword()) throw new AuthException(EErrorType.AUTH_PASSWORD_ERROR);
        authService.save(dto);
        return ResponseEntity.ok(dto.getSurname()+": kayit edildi");
    }


    @PostMapping("/login")
    public ResponseEntity<String> doLogin(@RequestBody AuthLoginDto dto){
        return ResponseEntity.ok(authService.doLogin(dto));
    }

    @PostMapping("/fanoutdeneme")
    public ResponseEntity<String> fanoutDeneme(@RequestBody String mesaj){
        authService.fanoutDeneme(mesaj);
        return ResponseEntity.ok("mesaj gönderildi");
    }
    @GetMapping("/gatewaydeneme")
    public ResponseEntity<String> gatewayMessageDeneme( String mesajj){
        return ResponseEntity.ok(authService.apideneme(mesajj));
    }

}

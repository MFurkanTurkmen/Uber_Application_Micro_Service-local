package com.furkan.controller;


import com.furkan.dto.request.AuthLoginDto;
import com.furkan.dto.request.RegisterDriverRequestDto;
import com.furkan.dto.request.RegisterPassengerRequestDto;
import com.furkan.service.AuthService;
import com.furkan.utility.RegisterExceptionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final RegisterExceptionControl registerExceptionControl;


    @PostMapping("/registerpassenger")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> registerPassenger(RegisterPassengerRequestDto dto) {
        registerExceptionControl.ControllerControl(dto.getPassword(),dto.getRePassword(),dto.getAge(),dto.getEmail(),dto.getName());
        return ResponseEntity.ok(authService.savePassenger(dto));
    }

    @PostMapping("/registerdriver")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> registerDriver(RegisterDriverRequestDto dto){
        registerExceptionControl.ControllerControl(dto.getPassword(),dto.getRePassword(),dto.getAge(),dto.getEmail(),dto.getName());
        return ResponseEntity.ok(authService.saveDriver(dto));
    }





}

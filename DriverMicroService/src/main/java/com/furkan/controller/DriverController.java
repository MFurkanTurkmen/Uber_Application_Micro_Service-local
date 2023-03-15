package com.furkan.controller;

import com.furkan.dto.request.TokenDto;
import com.furkan.repository.entity.Driver;
import com.furkan.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DriverController {

    private final DriverService driverService;

    @GetMapping("/getdriver")
    @PreAuthorize("hasAuthority('BASIC')")
    public ResponseEntity<List<Driver>> getDriver(){
        return ResponseEntity.ok(driverService.findAll());
    }
}

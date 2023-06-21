package com.furkan.controller;

import com.furkan.dto.DriverExpDto;
import com.furkan.dto.StartRideDto;
import com.furkan.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passenger")
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;

    @PostMapping("/setexpdriver")
    @PreAuthorize("hasAuthority('SLEEPER')")
    public ResponseEntity<String> setDriverExp(@RequestBody DriverExpDto dto) {
        return ResponseEntity.ok(passengerService.setDriverExp(dto));
    }

    @PostMapping("/startride")
    @PreAuthorize(value="hasAuthority('TRAVELER') or hasAuthority('SLEEPER') or hasAuthority('PLANETARY')")
    public ResponseEntity<String> startRide(@RequestBody StartRideDto dto) {
        return ResponseEntity.ok(passengerService.startRide(dto));
    }


}

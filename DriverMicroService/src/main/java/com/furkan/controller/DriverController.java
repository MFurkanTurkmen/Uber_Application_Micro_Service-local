package com.furkan.controller;

import com.furkan.dto.request.BaseDriverDto;
import com.furkan.dto.request.TokenDto;
import com.furkan.repository.entity.Driver;
import com.furkan.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DriverController {

    private final DriverService driverService;

    @GetMapping("/findall")
    @PreAuthorize("hasAuthority('BASIC')")
    public ResponseEntity<List<Driver>> getDriver(){
        return ResponseEntity.ok(driverService.findAll());
    }



    @GetMapping("/findall2")
    //@PreAuthorize("hasAuthority('MASTER')" + "|| hasAuthority('MIDDLE')")  // çalışıyor ikisinden birisi olsa yeter
    //@PreAuthorize(value="hasAuthority('MASTER') or hasAuthority('MIDDLE')") // çalışıyor ikisinden birisi.
    //@PreAuthorize("hasAuthority('MIDDLE')")
    @PreAuthorize(value="hasAuthority('MASTER') and hasAuthority('MIDDLE')") // çalışıyor ikisinden birisi olsa yeter
    public ResponseEntity<List<Driver>> getDriver2(){
        return ResponseEntity.ok(driverService.findAll());
    }

}

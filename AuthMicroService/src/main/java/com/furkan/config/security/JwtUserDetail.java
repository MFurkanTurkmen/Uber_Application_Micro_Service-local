package com.furkan.config.security;

import com.furkan.repository.entity.Autho;
import com.furkan.repository.entity.Driver;
import com.furkan.service.DriverAuthorizationsService;
import com.furkan.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class JwtUserDetail implements UserDetailsService {
    @Autowired
     DriverService driverService;
    @Autowired
    DriverAuthorizationsService authorizationsService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails getUserDetailObject(Long authid){
        Optional<Driver> driver = driverService.findByAuthId(authid);
        if (driver.isEmpty()) return null;
        List<GrantedAuthority> authorities= new ArrayList<>();
        for (Autho enums: authorizationsService.driverIdileYetkiListesi(driver.get().getId()) ){
            authorities.add(new SimpleGrantedAuthority(enums.name()));
        }

        return User.builder()
                .username(driver.get().getUsername())
                .password("")
                .authorities(authorities)
                .build();
    }
}

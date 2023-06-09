package com.furkan.config.security;

import com.furkan.repository.IPassengerAuthoRepository;
import com.furkan.repository.entity.Autho;
import com.furkan.repository.entity.Passenger;
import com.furkan.service.PassengerAuthoService;
import com.furkan.service.PassengerService;
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
     PassengerService passengerService;
    @Autowired
    PassengerAuthoService passengerAuthoService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails getUserDetailObject(Long authId){
        Optional<Passenger> passenger = passengerService.findByAuthId(authId);
        if (passenger.isEmpty()) return null;
        List<GrantedAuthority> authorities= new ArrayList<>();
        for (Autho x: passengerAuthoService.getPassengerAutho(passenger.get().getAuthId()) ){
            authorities.add(new SimpleGrantedAuthority(x.name()));
        }
        return User.builder()
                .username(passenger.get().getUsername())
                .password("")
                .authorities(authorities)
                .build();
    }
}

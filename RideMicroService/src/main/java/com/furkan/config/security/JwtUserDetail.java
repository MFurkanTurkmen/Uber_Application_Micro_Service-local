package com.furkan.config.security;

import com.furkan.repository.entity.Ride;
import com.furkan.service.RideService;
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
    RideService rideService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails getUserDetailObject(Long rideId) {
        Optional<Ride> ride = rideService.findById(rideId);
        if (ride.isEmpty()) return null;
        List<GrantedAuthority> authorities = new ArrayList<>();
        // authorities.add(new) hata olursa burada

        return User.builder()
                .username(String.valueOf(ride.get().getId()))
                .password("")
                .authorities(authorities)
                .build();
    }
}

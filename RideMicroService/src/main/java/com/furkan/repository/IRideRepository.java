package com.furkan.repository;

import com.furkan.repository.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRideRepository extends JpaRepository<Ride,Long> {
    Optional<Ride> findOptionalByPassengerId(Long passengerId);
    Optional<Ride> findByDriverId(Long driverId);
}

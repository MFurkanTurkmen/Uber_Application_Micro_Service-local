package com.furkan.repository;

import com.furkan.repository.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IRideRepository extends JpaRepository<Ride,Long> {
    Optional<List<Ride>> findOptionalByPassengerId(Long passengerId);
    Optional<List<Ride>> findOptionalByDriverId(Long driverId);
}

package com.furkan.repository;

import com.furkan.repository.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IPassengerRepository extends JpaRepository<Passenger,Long> {
    Optional<Passenger> findOptionalByUsername(String username);
    Optional<Passenger> findOptionalByAuthId(Long authId);
}

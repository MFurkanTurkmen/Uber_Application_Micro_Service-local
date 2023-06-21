package com.furkan.repository;

import com.furkan.repository.entity.PassengerAutho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPassengerAuthoRepository extends JpaRepository<PassengerAutho,Long> {
    Optional<List<PassengerAutho>> findOptionalByPassengerId(Long passenerId);
}

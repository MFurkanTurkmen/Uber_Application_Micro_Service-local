package com.furkan.repository;

import com.furkan.repository.entity.PassengerAutho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPassengerAuthoRepository extends JpaRepository<PassengerAutho,Long> {
    List<PassengerAutho> findByPassengerId(Long passenerId);
}

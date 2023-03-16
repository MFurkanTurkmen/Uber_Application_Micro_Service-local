package com.furkan.repository;

import com.furkan.repository.entity.DriverAutho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDriverAuthoRepository extends JpaRepository<DriverAutho,Long> {
    Optional<List<DriverAutho>> findOptionalByDriverId(Long driverId);
}

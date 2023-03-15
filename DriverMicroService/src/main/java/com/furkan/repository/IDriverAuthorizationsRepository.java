package com.furkan.repository;

import com.furkan.repository.entity.DriverAuthorizations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDriverAuthorizationsRepository extends JpaRepository<DriverAuthorizations,Long> {
    List<DriverAuthorizations> findByDriverId(Long driverId);
}

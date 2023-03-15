package com.furkan.repository;

import com.furkan.repository.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDriverRepository extends JpaRepository<Driver,Long> {
    Optional<Driver> findOptionalByUsername(String username);
    Optional<Driver> findOptionalByAuthId(Long authid);
}

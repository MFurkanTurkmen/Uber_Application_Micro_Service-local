package com.furkan.repository;

import com.furkan.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IAuthRepository extends JpaRepository<Auth,Long> {
    @Query("select COUNT(*)>0 from Auth a where a.email=?1")
    Boolean isEmail(String email);

}

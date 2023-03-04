package com.spring.vehiclerenting.repository;

import com.spring.vehiclerenting.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findById(Long id);

    @Override
    void deleteById(Long aLong);

    //    Application findByAppId(Long id);
}

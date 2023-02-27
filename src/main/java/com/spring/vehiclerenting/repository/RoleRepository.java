package com.spring.vehiclerenting.repository;

import com.spring.vehiclerenting.model.Role;
import com.spring.vehiclerenting.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(UserRoles name);
}

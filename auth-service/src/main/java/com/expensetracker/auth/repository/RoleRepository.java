package com.expensetracker.auth.repository;

import com.expensetracker.auth.model.ERole;
import com.expensetracker.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}

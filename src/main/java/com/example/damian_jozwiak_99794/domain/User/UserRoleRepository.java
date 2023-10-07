package com.example.damian_jozwiak_99794.domain.User;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
    Optional<UserRole> findByName(String name);
}
package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    // custom query methods
    // custom finder methods
    // extra methods db related operations
    Optional<User> findByEmail(String email);
}

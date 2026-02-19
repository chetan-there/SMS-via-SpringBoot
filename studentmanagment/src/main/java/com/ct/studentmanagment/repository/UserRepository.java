package com.ct.studentmanagment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ct.studentmanagment.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

    boolean existsByUsername(String username);
     
    Optional<Users> findByUsername(String username);

}

package com.assignment6.spring.repository;

import com.assignment6.spring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    <User> void findByEmail(String email);
}

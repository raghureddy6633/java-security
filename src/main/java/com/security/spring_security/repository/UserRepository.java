package com.security.spring_security.repository;

import com.security.spring_security.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo,Long> {

    Optional<UserInfo> findByUsername(String username);
}

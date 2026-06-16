package com.wildlens.wildlesnApi.wildlensApi.repository;

import com.wildlens.wildlesnApi.wildlensApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMailUser(String mailUser);
    boolean existsByMailUser(String mailUser);
}

package com.example.user.repository;

import com.example.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Find a user by their username
     */
    UserEntity findByUsername(String username);

    UserEntity findByUsernameAndPassword(String username, String password);

    /**
     * Find users by their role
     */
    List<UserEntity> findByRole(String role);

    /**
     * Check if a username is already taken
     */
    boolean existsByUsername(String username);
}

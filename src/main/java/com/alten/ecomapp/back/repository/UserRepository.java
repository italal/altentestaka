package com.alten.ecomapp.back.repository;

import com.alten.ecomapp.back.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    String USERS_BY_LOGIN_CACHE = "usersByLogin";

    String USERS_BY_EMAIL_CACHE = "usersByEmail";
   Optional<User> findOneByEmailIgnoreCase(String email);
    Optional<User> findOneByUsername(String login);


}

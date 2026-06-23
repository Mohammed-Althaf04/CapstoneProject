package com.wip.smartparking.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wip.smartparking.entity.User;
/**
 * Spring Data JPA repository interface providing database access methods for User entities.
 *
 * @author Naveen Muthu
 */

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailOrName(String email, String name);
}
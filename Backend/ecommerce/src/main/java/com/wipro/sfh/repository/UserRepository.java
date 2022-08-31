package com.wipro.sfh.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.sfh.entity.User;

/**
 * @author Umasankar
 * Modified Date: 25-08-2022
 * Description: user repository
 * 
 */

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	List<User> findAllByRole(String role);
}

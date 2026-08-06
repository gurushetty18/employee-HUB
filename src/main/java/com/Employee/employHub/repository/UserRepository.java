package com.Employee.employHub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Employee.employHub.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);

}

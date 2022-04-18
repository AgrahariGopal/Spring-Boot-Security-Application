package com.learnspringsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnspringsecurity.models.User;

public interface UserRepository extends JpaRepository<User, String> {

	public User findByUsername(String username);
	
	
}

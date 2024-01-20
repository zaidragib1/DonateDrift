package com.backend.DonateDrift.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.DonateDrift.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
	
}

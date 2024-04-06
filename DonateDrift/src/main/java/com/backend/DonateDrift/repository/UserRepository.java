package com.backend.DonateDrift.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.DonateDrift.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);


	@Query("select u from User u where u.id=:user_id")
	public User findUserById(@Param("user_id") Long user_id);
	
	@Query("select u from User u where u.email=:user_email")
	public User findUserByEmail(@Param("user_email") String user_email);
	
}

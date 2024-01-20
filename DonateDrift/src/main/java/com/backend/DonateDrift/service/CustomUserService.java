package com.backend.DonateDrift.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.DonateDrift.entity.User;
import com.backend.DonateDrift.repository.UserRepository;


@Service
public class CustomUserService implements UserDetailsService{

	private UserRepository userRepository;
	
	//autowire using constructor injection
	public CustomUserService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("user not found with email - "+username);
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), 
				user.getPassword(), authorities);
	}

}

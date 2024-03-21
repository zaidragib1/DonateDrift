package com.backend.DonateDrift.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.DonateDrift.config.JwtProvider;
import com.backend.DonateDrift.dtos.LoginRequest;
import com.backend.DonateDrift.dtos.SignupRequest;
import com.backend.DonateDrift.entity.User;
import com.backend.DonateDrift.exception.UserException;
import com.backend.DonateDrift.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	
	public UserService(UserRepository userRepository,JwtProvider jwtProvider){
		this.userRepository=userRepository;
		this.jwtProvider=jwtProvider;
	}
	
	public User findUserById(Long userId) throws UserException {
		Optional<User>user = userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		throw new UserException("user not with id "+userId);
	}

	public User findUserProfileByJwt(String jwt) throws UserException {
		
		String email = jwtProvider.getEmailFromToken(jwt);
		
		User user = userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("user not found with email " + email);
		}
		
		return user;
	}
	
	
	
}

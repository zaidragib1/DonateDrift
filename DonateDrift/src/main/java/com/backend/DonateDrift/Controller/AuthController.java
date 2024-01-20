package com.backend.DonateDrift.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.DonateDrift.config.JwtProvider;
import com.backend.DonateDrift.dtos.ApiResponse;
import com.backend.DonateDrift.dtos.AuthResponse;
import com.backend.DonateDrift.dtos.LoginRequest;
import com.backend.DonateDrift.dtos.SignupRequest;
import com.backend.DonateDrift.entity.User;
import com.backend.DonateDrift.exception.UserException;
import com.backend.DonateDrift.repository.UserRepository;
import com.backend.DonateDrift.service.CustomUserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtprovider;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserService customUserService;
	
//	@PostMapping("/signUp")
//	public ResponseEntity<ApiResponse> signup(@RequestBody SignupRequest signupRequest){
//		userService.signUp(signupRequest);
//		Authentication authentication = new UsernamePasswordAuthenticationToken
//				(savedUser.getEmail(),savedUser.getPassword());
//		
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		
//		String token = jwtprovider.generateToken(authentication);
//		return ResponseEntity.ok(new ApiResponse("user registerd successfully",true));
//	}
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest signupRequest) 
							throws UserException{
		
		String email = signupRequest.getEmail();
		String password = signupRequest.getPassword();
		String name = signupRequest.getName();
		
		//now check whether this email already exists
		User isEmailExist = userRepository.findByEmail(email);
		
		if(isEmailExist!=null) {
			throw new UserException("Email is Already used with another account");
		}
		
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setName(name);
		
		User savedUser = userRepository.save(createdUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken
				(savedUser.getEmail(),savedUser.getPassword());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtprovider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Signup Success");
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
		
	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginUserHandler
								(@RequestBody LoginRequest loginRequest){
		
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		Authentication authentication = authenticate(username,password);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtprovider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Signin Success");
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);

	}
	
	private Authentication authenticate(String username, String password) {
		
		UserDetails userDetails = customUserService.loadUserByUsername(username);
		
		if(userDetails==null) {
			throw new BadCredentialsException("Invalid Username");
		}
		
		if(!passwordEncoder.matches(password,userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid password... ");
		}
		
		return new UsernamePasswordAuthenticationToken
				(userDetails,null,userDetails.getAuthorities());
	}
	
	
}

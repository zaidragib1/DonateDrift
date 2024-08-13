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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.DonateDrift.config.JwtProvider;
import com.backend.DonateDrift.dtos.AuthResponse;
import com.backend.DonateDrift.dtos.LoginRequest;
import com.backend.DonateDrift.dtos.SignupRequest;
import com.backend.DonateDrift.dtos.TokenRequest;
import com.backend.DonateDrift.entity.User;
import com.backend.DonateDrift.exception.UserException;
import com.backend.DonateDrift.repository.UserRepository;
import com.backend.DonateDrift.service.CustomUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Base64;

@RestController
@CrossOrigin(origins = "https://donatedrifts.vercel.app", allowCredentials = "true")

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
		String userRole = signupRequest.getUserRole();
		
		//now check whether this email already exists
		User isEmailExist = userRepository.findByEmail(email);
		
		if(isEmailExist!=null) {
			throw new UserException("Email is Already used with another account");
		}
		
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setName(name);
		if ("ADMIN".equals(userRole)) {
			createdUser.setUserRole(userRole);
		}
		
		User savedUser = userRepository.save(createdUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken
				(savedUser.getEmail(),savedUser.getPassword());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtprovider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Signup Success");
		authResponse.setUserId(savedUser.getId());
		authResponse.setName(name);
		authResponse.setEmail(email);
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
		
	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginUserHandler
								(@RequestBody LoginRequest loginRequest){
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();

		User user = userRepository.findByEmail(username);
		Long userId = (user != null) ? user.getId() : null;
		
		Authentication authentication = authenticate(username,password);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtprovider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse();

		authResponse.setJwt(token);
		authResponse.setMessage("Signin Success");
		authResponse.setUserId(userId);
		authResponse.setEmail(user.getEmail());
		authResponse.setName(user.getName());
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);

		//wirking fine
	}
	
	@PostMapping("/token")
	 public ResponseEntity<User> findUserByToken(@RequestBody TokenRequest tokenRequest) throws UserException, JsonProcessingException {
		 
		 Base64.Decoder decoder = Base64.getUrlDecoder();
		
		 String header = new String(decoder.decode(tokenRequest.getHeader()));
		 String payload = new String(decoder.decode(tokenRequest.getPayload()));
		 System.out.println(header);System.out.println(payload);
	
		 ObjectMapper objectMapper = new ObjectMapper();
	
		 JsonNode jsonNode = objectMapper.readTree(payload);
	
		 // Get the value of the "email" field
		 String email = jsonNode.get("email").asText();
		 User user = userRepository.findByEmail(email);
		 if(user==null){
		 throw new UserException("User not exist");
		 }
		 return new ResponseEntity<User>(user,HttpStatus.OK);
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

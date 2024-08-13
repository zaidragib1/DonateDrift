package com.backend.DonateDrift.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.DonateDrift.dtos.updateUserRequest;
import com.backend.DonateDrift.entity.ProfilePicture;
import com.backend.DonateDrift.entity.User;
import com.backend.DonateDrift.exception.UserException;
import com.backend.DonateDrift.repository.ProfilePictureRepository;
import com.backend.DonateDrift.repository.UserRepository;
import com.backend.DonateDrift.service.CloudinaryImageService;

@RestController
@CrossOrigin(origins = "https://donatedrifts.vercel.app", allowCredentials = "true")
@RequestMapping("/api/user/update")
public class UserUpdateController {

	@Autowired
	private ProfilePictureRepository profilePictureRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CloudinaryImageService cloudinaryImageService;
	
	@PostMapping(value="/{id}", consumes = {"multipart/mixed", "multipart/form-data"})
	public ResponseEntity<User> updateUser(@ModelAttribute updateUserRequest updateUserRequest,@PathVariable Long id) throws UserException{
		
		User user = userRepository.findUserById(id);
		if(user==null) {
			throw new UserException("User not exist");
		}
		if(updateUserRequest.getName()!=null) {
			user.setName(updateUserRequest.getName());
		}
		if(updateUserRequest.getCity()!=null) {
			user.setCity(updateUserRequest.getCity());
		}
		if(updateUserRequest.getCountry()!=null) {
			user.setCountry(updateUserRequest.getCountry());
		}
		if(updateUserRequest.getDescription()!=null) {
			user.setDescription(updateUserRequest.getDescription());
		}
		if(updateUserRequest.getProfilePicUrl()!=null) {
			String data = this.cloudinaryImageService.upload(updateUserRequest.getProfilePicUrl());
			ProfilePicture profilePicture = new ProfilePicture();
			profilePicture.setUrl5(data);
			user.setProfilePicUrl(profilePicture);
			userRepository.save(user);
			profilePicture.setUser(user);
			profilePictureRepository.save(profilePicture);
		}else {
			userRepository.save(user);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
}

package com.backend.DonateDrift.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.DonateDrift.entity.User;
import com.backend.DonateDrift.repository.ProfilePictureRepository;
import com.backend.DonateDrift.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserUpdateController {

	@Autowired
	private ProfilePictureRepository pictureRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
}

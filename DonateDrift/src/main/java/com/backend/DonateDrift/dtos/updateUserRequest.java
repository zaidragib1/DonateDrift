package com.backend.DonateDrift.dtos;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class updateUserRequest {
	
	private String name;
	
	private MultipartFile profilePicUrl;
	
	private String description;
	
	private String city;
	
	private String country;
	
}

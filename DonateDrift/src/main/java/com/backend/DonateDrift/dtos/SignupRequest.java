package com.backend.DonateDrift.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequest {
	@NotBlank
	private String name;
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	
	
//
//	public SignupRequest() {
//
//	}
//
//
//
//
//	public SignupRequest(@NotBlank String name, @Email @NotBlank String email, @NotBlank String password) {
//		super();
//		this.name = name;
//		this.email = email;
//		this.password = password;
//	}
//
//
//
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
}

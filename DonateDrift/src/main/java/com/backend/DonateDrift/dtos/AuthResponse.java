package com.backend.DonateDrift.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
	private Long userId;
	private String jwt;
	private String message;
	private String name;
	private String email;
	
	
//	public AuthResponse() {
//
//	}
//	public AuthResponse(String jwt, String message) {
//		super();
//		this.jwt = jwt;
//		this.message = message;
//	}
//	public String getJwt() {
//		return jwt;
//	}
//	public void setJwt(String jwt) {
//		this.jwt = jwt;
//	}
//	public String getMessage() {
//		return message;
//	}
//	public void setMessage(String message) {
//		this.message = message;
//	}
	
	
}

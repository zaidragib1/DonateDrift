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

}

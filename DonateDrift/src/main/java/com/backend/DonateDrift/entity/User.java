package com.backend.DonateDrift.entity;

import com.backend.DonateDrift.enums.UserRole;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = true)
	private String name;

	private String password;

	private String profilePicUrl;

	private String userRole = "USER";


	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Fundraiser> fundraiser = new ArrayList<>();

}
//	public User() {
//
//	}
//
//	public User(Long id, String email, String name, String password, String profilePicUrl, UserRole role) {
//		super();
//		this.id = id;
//		this.email = email;
//		this.name = name;
//		this.password = password;
//		this.profilePicUrl = profilePicUrl;
//		this.role = role;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
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
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getProfilePicUrl() {
//		return profilePicUrl;
//	}
//
//	public void setProfilePicUrl(String profilePicUrl) {
//		this.profilePicUrl = profilePicUrl;
//	}
//
//	public UserRole getRole() {
//		return role;
//	}
//
//	public void setRole(UserRole role) {
//		this.role = role;
//	}
//
//
//
//
//}

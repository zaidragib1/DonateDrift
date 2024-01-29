package com.backend.DonateDrift.entity;

import java.util.ArrayList;
import java.util.List;

import com.backend.DonateDrift.enums.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;
	
	@Column(unique = true,nullable = false)
	private String email;
	
	@Column(nullable = true)
	private String name;
	
	private String password;
	
	private String profilePicUrl;
	
	private UserRole role;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Fundraiser> fundraiser = new ArrayList<>();
	

	public User() {
		
	}

	

	public List<Fundraiser> getFundraiser() {
		return fundraiser;
	}



	public void setFundraiser(List<Fundraiser> fundraiser) {
		this.fundraiser = fundraiser;
	}



	public User(Long id, String email, String name, String password, String profilePicUrl, UserRole role,
			List<Fundraiser> fundraiser) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.profilePicUrl = profilePicUrl;
		this.role = role;
		this.fundraiser = fundraiser;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	
	
	
}

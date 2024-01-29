package com.backend.DonateDrift.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.backend.DonateDrift.enums.Category;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Fundraiser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String title;
	
	private Category category;
	
	private String country;
	
	private String city;
	
	private String firstName;
	
	private String lastName;
	
	private String description;
	
	private List<String>photoUrl;
	
	private String videoUrl;
	
	private List<String>socialMediaLinks;
	
	private String upiId;
	
	private long requiredAmount;
	
	private long raisedAmount;
	
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "fundraiser",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Status> status=new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy = "fundraiser",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Donor> donors=new ArrayList<>();

	public Fundraiser(long id, String title, Category category, String country, String city, String firstName,
			String lastName, String description, List<String> photoUrl, String videoUrl, List<String> socialMediaLinks,
			String upiId, long requiredAmount, long raisedAmount, LocalDateTime createdAt, List<Status> status,
			User user, List<Donor> donors) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.country = country;
		this.city = city;
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.photoUrl = photoUrl;
		this.videoUrl = videoUrl;
		this.socialMediaLinks = socialMediaLinks;
		this.upiId = upiId;
		this.requiredAmount = requiredAmount;
		this.raisedAmount = raisedAmount;
		this.createdAt = createdAt;
		this.status = status;
		this.user = user;
		this.donors = donors;
	}

	public Fundraiser() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(List<String> photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public List<String> getSocialMediaLinks() {
		return socialMediaLinks;
	}

	public void setSocialMediaLinks(List<String> socialMediaLinks) {
		this.socialMediaLinks = socialMediaLinks;
	}

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	public long getRequiredAmount() {
		return requiredAmount;
	}

	public void setRequiredAmount(long requiredAmount) {
		this.requiredAmount = requiredAmount;
	}

	public long getRaisedAmount() {
		return raisedAmount;
	}

	public void setRaisedAmount(long raisedAmount) {
		this.raisedAmount = raisedAmount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Status> getStatus() {
		return status;
	}

	public void setStatus(List<Status> status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Donor> getDonors() {
		return donors;
	}

	public void setDonors(List<Donor> donors) {
		this.donors = donors;
	}
	
	
	
	
	
}

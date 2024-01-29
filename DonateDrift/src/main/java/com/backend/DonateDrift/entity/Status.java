package com.backend.DonateDrift.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String description;
	
	private String photoUrl;
	
	@ManyToOne
	@JoinColumn(name="fundraiser_id")
	private Fundraiser fundraiser;

	public Status(long id, String description, String photoUrl, Fundraiser fundraiser) {
		super();
		this.id = id;
		this.description = description;
		this.photoUrl = photoUrl;
		this.fundraiser = fundraiser;
	}

	public Status() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Fundraiser getFundraiser() {
		return fundraiser;
	}

	public void setFundraiser(Fundraiser fundraiser) {
		this.fundraiser = fundraiser;
	}
	
	
	
	
	
}

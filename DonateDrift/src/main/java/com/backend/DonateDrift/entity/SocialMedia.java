package com.backend.DonateDrift.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SocialMedia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String links;
	
	private String linkName;
	
	@ManyToOne
	@JoinColumn(name="fundraiser_id")
	private Fundraiser fundraiser;
	
	public SocialMedia(long id, String links, String linkName, Fundraiser fundraiser) {
		super();
		this.id = id;
		this.links = links;
		this.linkName = linkName;
		this.fundraiser = fundraiser;
	}

	public Fundraiser getFundraiser() {
		return fundraiser;
	}

	public void setFundraiser(Fundraiser fundraiser) {
		this.fundraiser = fundraiser;
	}

	public SocialMedia() {
		//super();
		// TODO Auto-generated constructor stub
	}

	public SocialMedia(long id, String links, String linkName) {
		super();
		this.id = id;
		this.links = links;
		this.linkName = linkName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLinks() {
		return links;
	}

	public void setLinks(String links) {
		this.links = links;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}


	
	
	
}

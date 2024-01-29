package com.backend.DonateDrift.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Donor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	private long amount;
	
	private String comment;
	
	private LocalDateTime donatedAt;
	
	@ManyToOne
	@JoinColumn(name="fundraiser_id")
	private Fundraiser fundraiser;

	public Donor(long id, String name, long amount, String comment, LocalDateTime donatedAt, Fundraiser fundraiser) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.comment = comment;
		this.donatedAt = donatedAt;
		this.fundraiser = fundraiser;
	}

	public Donor() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getDonatedAt() {
		return donatedAt;
	}

	public void setDonatedAt(LocalDateTime donatedAt) {
		this.donatedAt = donatedAt;
	}

	public Fundraiser getFundraiser() {
		return fundraiser;
	}

	public void setFundraiser(Fundraiser fundraiser) {
		this.fundraiser = fundraiser;
	}
	
	
	
	
	
	
	
}

package com.backend.DonateDrift.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
public class Donor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="donor_id")
	private Long id;
	//@Builder.Default
	private String name = "Anonymous";
	
	private long userId;
	
	private long amount;
	
	private String comment;
	
	private LocalDateTime donatedAt;
	
	@ManyToOne
	@JoinColumn(name="fundraiser_id")
	@JsonIgnore
	private Fundraiser fundraiser;
	
	@Transient
	private long fundraiserId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public long getFundraiserId() {
		return fundraiserId;
	}

	public void setFundraiserId(long fundraiserId) {
		this.fundraiserId = fundraiserId;
	}
	
	

}

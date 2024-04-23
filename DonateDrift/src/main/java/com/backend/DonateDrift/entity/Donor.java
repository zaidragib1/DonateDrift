package com.backend.DonateDrift.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Donor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="donor_id")
	private Long id;
	//@Builder.Default
	private String name;// = "Anonymous";
	
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


}

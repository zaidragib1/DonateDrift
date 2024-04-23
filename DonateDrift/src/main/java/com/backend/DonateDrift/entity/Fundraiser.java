package com.backend.DonateDrift.entity;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.backend.DonateDrift.enums.Category;
import com.backend.DonateDrift.entity.Attachment;

import com.backend.DonateDrift.enums.FundraiserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fundraiser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fundraiser_id")
	private long id;

	private String title;

	private Category category;

	private String country;

	private String city;

	private String firstName;

	private String lastName;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String description;

	@OneToOne(mappedBy = "fundraiser", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private CoverAttachment coverAttachment;

	@OneToMany(mappedBy = "fundraiser", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Attachment> attachment = new ArrayList<>();

	private FundraiserStatus fundraiserStatus = FundraiserStatus.PENDING;

	private String videoUrl;

	@OneToMany(mappedBy = "fundraiser", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<SocialMedia> socialMediaLinks = new ArrayList<>();

	private String upiId;

	private Long requiredAmount;

	private Long raisedAmount;

	private LocalDateTime createdAt;

	@OneToMany(mappedBy = "fundraiser", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Status> status = new ArrayList<>();
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "fundraiser", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Donor> donors = new ArrayList<>();

	@Override
	public int hashCode() {
		return Objects.hash(id, title, category, country, city, firstName, lastName, description, fundraiserStatus, videoUrl, upiId, requiredAmount, raisedAmount, createdAt);
	}

}




	


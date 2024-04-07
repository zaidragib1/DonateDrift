package com.backend.DonateDrift.entity;

import java.util.List;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfilePicture {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String url5;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name="user_id")
    private User user;

    @Override
    public int hashCode() {
        return Objects.hash(id, url5);
    }

}

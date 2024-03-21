package com.backend.DonateDrift.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoverAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int status1;
    private String message1;
    private String url1;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name="fundraiser_id")
    private Fundraiser fundraiser;

    @Override
    public int hashCode() {
        return Objects.hash(id, url1, message1, status1);
    }
}

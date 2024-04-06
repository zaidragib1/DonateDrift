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
public class StatusAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String url2;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="status_id")
    private Status status;


    @Override
    public int hashCode() {
        return Objects.hash(id, url2);
    }
}

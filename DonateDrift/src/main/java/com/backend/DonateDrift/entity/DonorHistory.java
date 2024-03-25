//package com.backend.DonateDrift.entity;
//
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class DonorHistory {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private long amount;
//
//    private LocalDateTime donatedAt;
//
//    @OneToMany(mappedBy = "donorHistory", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Fundraiser> fundraiser = new ArrayList<>();
//
//    @OneToOne
//    @JsonIgnore
//    @JoinColumn(name = "user_id")
//    private User user;
//
//}

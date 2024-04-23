package com.backend.DonateDrift.entity;

import jakarta.persistence.*;
import com.backend.DonateDrift.entity.Fundraiser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private Long fundraiserId;
    private String paypalTransactionId;
    private String paypalPaymentStatus;
    private String paypalPayerId;

}

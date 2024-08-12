package com.backend.DonateDrift.repository;

import com.backend.DonateDrift.entity.Donor;
import com.backend.DonateDrift.entity.Fundraiser;
import com.backend.DonateDrift.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {


}
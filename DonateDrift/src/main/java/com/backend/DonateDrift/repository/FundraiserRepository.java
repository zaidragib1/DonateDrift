package com.backend.DonateDrift.repository;

import com.backend.DonateDrift.entity.Fundraiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundraiserRepository extends JpaRepository<Fundraiser, Long> {
}

package com.backend.DonateDrift.repository;

import com.backend.DonateDrift.entity.Donor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {

	
	
}

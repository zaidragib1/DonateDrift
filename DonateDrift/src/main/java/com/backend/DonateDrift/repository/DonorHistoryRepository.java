package com.backend.DonateDrift.repository;

import com.backend.DonateDrift.entity.Donor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorHistoryRepository extends JpaRepository<Donor,Long> {
	
	List<Donor> findByUserId(Long userId);
	
}

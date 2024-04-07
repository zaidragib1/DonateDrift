package com.backend.DonateDrift.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.DonateDrift.entity.Donor;
import com.backend.DonateDrift.repository.DonorHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonorHistoryService {

	private final DonorHistoryRepository donorHistoryRepository;

	 public List<Donor> getDonorHistoryByUserId(Long userId) {
		 List<Donor> donorHistory = donorHistoryRepository.findByUserId(userId);
		 for (Donor donor : donorHistory) {
		 if (donor.getFundraiser() != null) {
		 donor.setFundraiserId(donor.getFundraiser().getId());
		 }
		 }
		 return donorHistory;
	 }
	
	
}

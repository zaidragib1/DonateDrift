package com.backend.DonateDrift.service;

import com.backend.DonateDrift.entity.Donor;
import com.backend.DonateDrift.repository.DonorHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonorHistoryService {

    private final DonorHistoryRepository donorHistoryRepository;

    public List<Donor> getDonorHistoryByUserId(Long userId) {
        List<Donor> donorHistory = donorHistoryRepository.findByUserId(userId);
        for (Donor donor : donorHistory) {
            if (donor.getFundraiser() != null) {
                donor.setFundraiserTitle(donor.getFundraiser().getTitle());
            }
        }
        return donorHistory;
    }



}

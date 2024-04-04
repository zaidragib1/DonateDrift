package com.backend.DonateDrift.service;

import com.backend.DonateDrift.dtos.DonorRequest;
import com.backend.DonateDrift.entity.Donor;
//import com.backend.DonateDrift.entity.DonorHistory;
import com.backend.DonateDrift.entity.Fundraiser;
import com.backend.DonateDrift.entity.User;
import com.backend.DonateDrift.exception.FundRaiserException;
import com.backend.DonateDrift.repository.DonorHistoryRepository;
import com.backend.DonateDrift.repository.DonorRepository;
import com.backend.DonateDrift.repository.FundraiserRepository;
import com.backend.DonateDrift.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DonorService {

    @Autowired
    private DonorRepository donorRepository;
    @Autowired
    private FundraiserRepository fundraiserRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DonorHistoryRepository donorHistoryRepository;

//    public List<Donor> getDonorHistoryByUserId(Long userId, Long fundraiserId) throws FundRaiserException {
//        Optional<Fundraiser> optionalFundraiser = fundraiserRepository.findById(fundraiserId);
//        if (optionalFundraiser.isEmpty()) {
//            throw new FundRaiserException("Fundraiser not found with ID: " + fundraiserId);
//        }
//
//        Fundraiser fundraiser = optionalFundraiser.get();
//        String fundraiserTitle = fundraiser.getTitle();
//
//        List<Donor> donorHistory = donorHistoryRepository.findByUserIdAndFundraiserId(userId, fundraiserId);
//
//        // Set the fundraiser title for each donor in the history
//        for (Donor donor : donorHistory) {
//            donor.setFundraiserTitle(fundraiserTitle);
//        }
//
//        return donorHistory;
//    }
    public Donor addDonor(DonorRequest donorRequest,Long id,Long userId) throws FundRaiserException {
        Donor donor = new Donor();
        donor.setUserId(userId);
        donor.setName(donorRequest.getName());
        donor.setAmount(donorRequest.getAmount());
        donor.setComment(donorRequest.getComment());
        donor.setDonatedAt(LocalDateTime.now());

        Long fundraiserId = id;
        Optional<Fundraiser> optionalFundraiser = fundraiserRepository.findById(fundraiserId);
        if (optionalFundraiser.isPresent()) {
            Fundraiser fundraiser = optionalFundraiser.get();
            long currentRaisedAmount = fundraiser.getRaisedAmount();
            long newRaisedAmount = currentRaisedAmount + donorRequest.getAmount();
            fundraiser.setRaisedAmount(newRaisedAmount);
            fundraiserRepository.save(fundraiser);
            donor.setFundraiser(fundraiser);
        } else {
            throw new FundRaiserException("Fundraiser Not Found!!");
        }
        return donorRepository.save(donor);
    }


    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    public Donor getDonorById(Long id) {
        Optional<Donor> optionalDonor = donorRepository.findById(id);
        return optionalDonor.orElse(null);
    }

    public Donor updateDonor(Long id, DonorRequest donorRequest) {
        Optional<Donor> optionalDonor = donorRepository.findById(id);
        if (optionalDonor.isPresent()) {
            Donor donor = optionalDonor.get();
            donor.setName(donorRequest.getName());
            donor.setAmount(donorRequest.getAmount());
            donor.setComment(donorRequest.getComment());
            donor.setDonatedAt(LocalDateTime.now());
            return donorRepository.save(donor);
        }
        return null;
    }

    public void deleteDonor(Long id) {
        donorRepository.deleteById(id);
    }
}

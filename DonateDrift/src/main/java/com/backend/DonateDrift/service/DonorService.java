package com.backend.DonateDrift.service;

import com.backend.DonateDrift.dtos.DonorRequest;
import com.backend.DonateDrift.entity.Donor;
//import com.backend.DonateDrift.entity.DonorHistory;
import com.backend.DonateDrift.entity.Fundraiser;
import com.backend.DonateDrift.entity.User;
import com.backend.DonateDrift.exception.FundRaiserException;
//import com.backend.DonateDrift.repository.DonorHistoryRepository;
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

//    @Autowired
//    private DonorHistoryRepository donorHistoryRepository;
//
//    public Donor addDonor(DonorRequest donorRequest,Long id,Long user_id) throws FundRaiserException {
//        Donor donor = new Donor();
//        donor.setName(donorRequest.getName());
//        donor.setAmount(donorRequest.getAmount());
//        donor.setComment(donorRequest.getComment());
//        donor.setDonatedAt(LocalDateTime.now());
//
//        User user = userRepository.findUserById(user_id);
//        DonorHistory donorHistory = new DonorHistory();
//        donorHistory.setAmount(donorRequest.getAmount());
//        donorHistory.setDonatedAt(LocalDateTime.now());
//        user.getDonorHIstory().add(donorHistory);
//        userRepository.save(user);
//        donorHistory.setUser(user);
//        //donorHistoryRepository.save(donorHistory);
//
//
//        Long fundraiserId = id;
//        Optional<Fundraiser> optionalFundraiser = fundraiserRepository.findById(fundraiserId);
//        if (optionalFundraiser.isPresent()) {
//            Fundraiser fundraiser = optionalFundraiser.get();
//            long currentRaisedAmount = fundraiser.getRaisedAmount();
//            long newRaisedAmount = currentRaisedAmount + donorRequest.getAmount();
//            fundraiser.setRaisedAmount(newRaisedAmount);
//            fundraiserRepository.save(fundraiser);
//            donor.setFundraiser(fundraiser);
//            donorHistory.getFundraiser().add(fundraiser);
//        } else {
//            throw new FundRaiserException("Fundraiser Not Found!!");
//        }
//        return donorRepository.save(donor);
//    }

//    public Donor addDonor(DonorRequest donorRequest) {
//        Donor donor = new Donor();
//        donor.setName(donorRequest.getName());
//        donor.setAmount(donorRequest.getAmount());
//        donor.setComment(donorRequest.getComment());
//        donor.setDonatedAt(LocalDateTime.now());
//
//        // Find the fundraiser by its ID
//        Long fundraiserId = donorRequest.getFundraiserId();
//        Fundraiser fundraiser = fundraiserRepository.findById(fundraiserId)
//                .orElseThrow(() -> new RuntimeException("Fundraiser not found with id: " + fundraiserId));
//
//        // Update the raised amount of the fundraiser
//        long currentRaisedAmount = fundraiser.getRaisedAmount();
//        long newRaisedAmount = currentRaisedAmount + donorRequest.getAmount();
//        fundraiser.setRaisedAmount(newRaisedAmount);
//
//        // Save the updated fundraiser
//        fundraiserRepository.save(fundraiser);
//
//        // Set the fundraiser for the donor and save the donor
//        donor.setFundraiser(fundraiser);
//        return donorRepository.save(donor);
//    }

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

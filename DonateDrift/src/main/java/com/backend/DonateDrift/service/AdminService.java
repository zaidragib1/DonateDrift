package com.backend.DonateDrift.service;

import com.backend.DonateDrift.entity.Fundraiser;
import com.backend.DonateDrift.enums.FundraiserStatus;
import com.backend.DonateDrift.repository.FundraiserRepository;
import com.backend.DonateDrift.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FundraiserRepository fundraiserRepository;

    public List<Fundraiser> getAllFundraiser(){
        return fundraiserRepository.findAll();
    }

    public Fundraiser updateFundraiser(long fundraiser_id,long st){
        Fundraiser fundraiser = fundraiserRepository.findById(fundraiser_id).orElse(null);
        if(st==1){
            fundraiser.setFundraiserStatus(FundraiserStatus.ACCEPTED);
            return fundraiserRepository.save(fundraiser);
        }
        fundraiser.setFundraiserStatus(FundraiserStatus.REJECTED);
        return fundraiserRepository.save(fundraiser);
    }

    public List<Fundraiser> getAccepted(){
        return fundraiserRepository.getAllAccepted();
    }

    public List<Fundraiser> getRejected(){
        return fundraiserRepository.getAllRejected();
    }

    public List<Fundraiser> getPending(){
        return fundraiserRepository.getAllPending();
    }

}

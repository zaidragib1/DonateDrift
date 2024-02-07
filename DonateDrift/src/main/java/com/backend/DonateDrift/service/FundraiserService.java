package com.backend.DonateDrift.service;

import com.backend.DonateDrift.dtos.FundraiserRequest;
import com.backend.DonateDrift.Controller.FundraiserController;
import com.backend.DonateDrift.entity.Fundraiser;
import com.backend.DonateDrift.repository.FundraiserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundraiserService{

    private final FundraiserRepository fundraiserRepository;
    @Autowired
    public FundraiserService(FundraiserRepository fundraiserRepository){
        this.fundraiserRepository = fundraiserRepository;
    }

    public List<Fundraiser> getAllFundraisers() {
        return fundraiserRepository.findAll();
    }


    public Fundraiser getFundraiserById(long id) {
        return fundraiserRepository.findById(id).orElse(null);
    }


    public Fundraiser createFundraiser(FundraiserRequest fundraiserRequest) {
        Fundraiser fundraiser = new Fundraiser();
        BeanUtils.copyProperties(fundraiserRequest, fundraiser);
        //System.out.println(fundraiserRequest.getAttachmentUrl().size());
        //fundraiser.getattachementUrl(fundraiserRequest.getAttachmentUrl());
        return fundraiserRepository.save(fundraiser);
    }


    public Fundraiser updateFundraiser(long id, FundraiserRequest fundraiserRequest) {
        Fundraiser existingFundraiser = fundraiserRepository.findById(id).orElse(null);
        if (existingFundraiser != null) {
            BeanUtils.copyProperties(fundraiserRequest, existingFundraiser);
            return fundraiserRepository.save(existingFundraiser);
        }
        return null;
    }


    public void deleteFundraiser(long id) {
        fundraiserRepository.deleteById(id);
    }
}


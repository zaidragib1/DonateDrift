package com.backend.DonateDrift.service;

import com.backend.DonateDrift.dtos.FundraiserRequest;

import com.backend.DonateDrift.entity.Attachment;
import com.backend.DonateDrift.entity.Fundraiser;
import com.backend.DonateDrift.entity.User;
import com.backend.DonateDrift.exception.UserException;
import com.backend.DonateDrift.repository.FundraiserRepository;
import com.backend.DonateDrift.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FundraiserService{

    private final int adminId=434;
    private final FundraiserRepository fundraiserRepository;

    private final UserRepository userRepository;

    private final UserService userService;


    @Autowired
    public FundraiserService(FundraiserRepository fundraiserRepository,UserRepository userRepository,UserService userService){
        this.fundraiserRepository = fundraiserRepository;
        this.userRepository=userRepository;
        this.userService=userService;
    }

    public List<Fundraiser> getAllFundraisers() {
        return fundraiserRepository.findAll();
    }


    public Fundraiser getFundraiserById(long id) {
        return fundraiserRepository.findById(id).orElse(null);
    }


    public Fundraiser createFundraiser(FundraiserRequest fundraiserRequest,Long id) throws UserException, GeneralSecurityException, IOException {
        Fundraiser fundraiser = new Fundraiser();
        BeanUtils.copyProperties(fundraiserRequest, fundraiser);
        fundraiser.setCreatedAt(LocalDateTime.now());

//        // Upload images to Google Drive
//        List<Attachment> attachments = new ArrayList<>();
//        for (Attachment res : fundraiserRequest.getAttachment()) {
//            Attachment attachment = fileService.uploadImageToDrive(new File(res.getUrl()));
//            attachments.add(attachment);
//        }
        //fundraiser.setAttachment(attachments);


        Long userId = id;
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.getFundraiser().add(fundraiser);
            userRepository.save(user);
            fundraiser.setUser(user);
        } else {
            throw new UserException("User Not Found!!");
        }
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


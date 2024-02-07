package com.backend.DonateDrift.service;

import com.backend.DonateDrift.dtos.StatusRequest;
import com.backend.DonateDrift.entity.Fundraiser;
import com.backend.DonateDrift.entity.Status;
import com.backend.DonateDrift.repository.FundraiserRepository;
import com.backend.DonateDrift.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatusService {

    private final StatusRepository statusRepository;
    private final FundraiserRepository fundraiserRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository, FundraiserRepository fundraiserRepository) {
        this.statusRepository = statusRepository;
        this.fundraiserRepository = fundraiserRepository;
    }

    public Status addStatus(Long fundraiserId, StatusRequest statusRequest) {
        Fundraiser fundraiser = fundraiserRepository.findById(fundraiserId)
                .orElseThrow(() -> new RuntimeException("Fundraiser not found"));

        Status status = new Status();
        status.setDescription(statusRequest.getDescription());
        status.setPhotoUrl(statusRequest.getPhotoUrl());
        status.setFundraiser(fundraiser);
        status.setUpdatedAt(LocalDateTime.now());

        return statusRepository.save(status);
    }

    public Status getStatusById(Long statusId) {
        return statusRepository.findById(statusId)
                .orElseThrow(() -> new RuntimeException("Status not found"));
    }

    public List<Status> getStatusByFundraiserId(Long fundraiserId) {
        return statusRepository.findByFundraiserId(fundraiserId);
    }

    public Status updateStatus(Long statusId, StatusRequest statusRequest) {
        Status status = statusRepository.findById(statusId)
                .orElseThrow(() -> new RuntimeException("Status not found"));
        status.setDescription(statusRequest.getDescription());
        status.setPhotoUrl(statusRequest.getPhotoUrl());
        status.setUpdatedAt(LocalDateTime.now());
        return statusRepository.save(status);
    }

    public void deleteStatus(Long statusId) {
        Status status = statusRepository.findById(statusId)
                .orElseThrow(() -> new RuntimeException("Status not found"));
        statusRepository.delete(status);
    }
}

package com.backend.DonateDrift.Controller;

import com.backend.DonateDrift.entity.Donor;
import com.backend.DonateDrift.repository.DonorHistoryRepository;
import com.backend.DonateDrift.service.DonorHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/donor-history")
@RequiredArgsConstructor
public class DonorHistoryController {

    private final DonorHistoryService donorHistoryService;
    private final DonorHistoryRepository donorHistoryRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Donor>> getDonorHistoryByUserId(@PathVariable Long userId) {
        List<Donor> donorHistory = donorHistoryService.getDonorHistoryByUserId(userId);
        return new ResponseEntity<>(donorHistory, HttpStatus.OK);
    }
}


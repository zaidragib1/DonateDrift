package com.backend.DonateDrift.Controller;

import com.backend.DonateDrift.dtos.DonorRequest;
import com.backend.DonateDrift.entity.Donor;
import com.backend.DonateDrift.exception.FundRaiserException;
import com.backend.DonateDrift.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    @Autowired
    private DonorService donorService;

    @PostMapping("/{id}/{userId}")
    public ResponseEntity<Donor> addDonor(@RequestBody DonorRequest donorRequest, @PathVariable Long id,@PathVariable Long userId) throws FundRaiserException {
    	Donor donor = donorService.addDonor(donorRequest,id,userId);
    	return new ResponseEntity<>(donor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Donor>> getAllDonors() {
        List<Donor> donors = donorService.getAllDonors();
        return new ResponseEntity<>(donors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donor> getDonorById(@PathVariable Long id) {
        Donor donor = donorService.getDonorById(id);
        return new ResponseEntity<>(donor, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable Long id, @RequestBody DonorRequest donorRequest) {
        Donor donor = donorService.updateDonor(id, donorRequest);
        return new ResponseEntity<>(donor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonor(@PathVariable Long id) {
        donorService.deleteDonor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

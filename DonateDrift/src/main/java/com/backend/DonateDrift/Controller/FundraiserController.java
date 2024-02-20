package com.backend.DonateDrift.Controller;

import com.backend.DonateDrift.dtos.FundraiserRequest;
import com.backend.DonateDrift.entity.Fundraiser;
import com.backend.DonateDrift.exception.UserException;
import com.backend.DonateDrift.service.FundraiserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fundraisers")
public class FundraiserController {

    private final FundraiserService fundraiserService;

    @Autowired
    public FundraiserController(FundraiserService fundraiserService) {
        this.fundraiserService = fundraiserService;
    }

    @GetMapping
    public ResponseEntity<List<Fundraiser>> getAllFundraisers() {
        List<Fundraiser> fundraisers = fundraiserService.getAllFundraisers();
        return new ResponseEntity<>(fundraisers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fundraiser> getFundraiserById(@PathVariable long id) {
        Fundraiser fundraiser = fundraiserService.getFundraiserById(id);
        return new ResponseEntity<>(fundraiser, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Fundraiser> createFundraiser(@RequestBody FundraiserRequest fundraiserRequest,@PathVariable Long id) throws UserException {
        Fundraiser createdFundraiser = fundraiserService.createFundraiser(fundraiserRequest,id);
        return new ResponseEntity<>(createdFundraiser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fundraiser> updateFundraiser(@PathVariable long id, @RequestBody FundraiserRequest fundraiserRequest) {
        Fundraiser updatedFundraiser = fundraiserService.updateFundraiser(id, fundraiserRequest);
        return new ResponseEntity<>(updatedFundraiser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFundraiser(@PathVariable long id) {
        fundraiserService.deleteFundraiser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

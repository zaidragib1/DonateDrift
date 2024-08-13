package com.backend.DonateDrift.Controller;

import com.backend.DonateDrift.entity.Fundraiser;
import com.backend.DonateDrift.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://donatedrifts.vercel.app", allowCredentials = "true")

@RequestMapping("/api/admin")

public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/fundraisers")
    public List<Fundraiser> getAllFundraisers() {
        return adminService.getAllFundraiser();
    }

    @PutMapping("/fundraisers/{fundraiser_id}/{status}")
    public Fundraiser updateFundraiserStatus(@PathVariable("fundraiser_id") long fundraiserId, @PathVariable("status") Long status) {
        return adminService.updateFundraiser(fundraiserId, status);
    }

    @GetMapping("/accepted")
    public List<Fundraiser> getAccepted(){
        return adminService.getAccepted();
    }

    @GetMapping("/rejected")
    public List<Fundraiser> getRejected(){
        return adminService.getRejected();
    }

    @GetMapping("/pending")
    public List<Fundraiser> getPending(){
        return adminService.getPending();
    }



}


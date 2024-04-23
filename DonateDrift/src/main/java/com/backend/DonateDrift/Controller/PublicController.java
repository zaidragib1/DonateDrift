package com.backend.DonateDrift.Controller;

import com.backend.DonateDrift.entity.Fundraiser;
import com.backend.DonateDrift.enums.Category;
import com.backend.DonateDrift.service.FundraiserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fundraisers")
public class PublicController {

    @Autowired
    private FundraiserService fundraiserService;

    @GetMapping
    public ResponseEntity<Page<Fundraiser>> getAllFundraisers(@RequestParam Integer pageNumber,
                                                              @RequestParam Integer pageSize) {
        Page<Fundraiser> fundraisers = fundraiserService.getAllFundraisers(pageNumber,pageSize);
        return new ResponseEntity<>(fundraisers, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<Fundraiser>> getByCountry(@RequestParam("country") String country, @RequestParam("city") String city, @RequestParam("category") Category category, @RequestParam Integer pageNumber,
                                                         @RequestParam Integer pageSize) {


        if(country!=null && !country.equals("null")){
            Page<Fundraiser> p = fundraiserService.findByCountry(country,pageNumber,pageSize);
            return new ResponseEntity<>(p,HttpStatus.OK);
        }

        if(city!=null && !city.equals("null")){
            Page<Fundraiser> p = fundraiserService.findByCity(city,pageNumber,pageSize);
            return new ResponseEntity<>(p,HttpStatus.OK);
        }

        if(category!=null){
            Page<Fundraiser> p = fundraiserService.findByCategory(category,pageNumber,pageSize);
            return new ResponseEntity<>(p,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

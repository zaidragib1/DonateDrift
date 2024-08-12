package com.backend.DonateDrift.Controller;

import com.backend.DonateDrift.entity.Fundraiser;
import com.backend.DonateDrift.entity.Status;
import com.backend.DonateDrift.enums.Category;
import com.backend.DonateDrift.repository.FundraiserRepository;
import com.backend.DonateDrift.service.FundraiserService;
import com.backend.DonateDrift.service.StatusService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {
	
	@Autowired
	private StatusService statusService;

    @Autowired
    private FundraiserService fundraiserService;
    
    @Autowired
    public FundraiserRepository fundraiserRepository;

    @GetMapping("/fundraisers")
    public ResponseEntity<Page<Fundraiser>> getAllFundraisers(@RequestParam Integer pageNumber,
                                                              @RequestParam Integer pageSize) {
        Page<Fundraiser> fundraisers = fundraiserService.getAllFundraisers(pageNumber,pageSize);
        return new ResponseEntity<>(fundraisers, HttpStatus.OK);
    }

    @GetMapping("/fundraisers/filter")
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
    
    @GetMapping("/status/{fundraiserId}")
    public ResponseEntity<List<Status>> getStatusByFundraiserId(@PathVariable Long fundraiserId) {
        List<Status> statuses = statusService.getStatusByFundraiserId(fundraiserId);
        return ResponseEntity.ok().body(statuses);
    }
    
    @GetMapping("/fundraisers/{id}")
    public ResponseEntity<Fundraiser> getFundraiserById(@PathVariable long id) {
    	Fundraiser fundraiser = fundraiserRepository.getAllById(id);
    	return new ResponseEntity<>(fundraiser,HttpStatus.OK);
    }

}

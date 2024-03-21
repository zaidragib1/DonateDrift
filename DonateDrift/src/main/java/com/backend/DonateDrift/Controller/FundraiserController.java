package com.backend.DonateDrift.Controller;

import com.backend.DonateDrift.dtos.FundraiserRequest;
import com.backend.DonateDrift.entity.Attachment;
import com.backend.DonateDrift.entity.CoverAttachment;
import com.backend.DonateDrift.entity.Fundraiser;
import com.backend.DonateDrift.entity.User;
import com.backend.DonateDrift.exception.UserException;
import com.backend.DonateDrift.repository.AttachmentRepository;
import com.backend.DonateDrift.repository.CoverAttachmentRepository;
import com.backend.DonateDrift.repository.FundraiserRepository;
import com.backend.DonateDrift.repository.UserRepository;
import com.backend.DonateDrift.service.FileService;
import com.backend.DonateDrift.service.FundraiserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fundraisers")
public class FundraiserController {

    private final FundraiserService fundraiserService;

    @Autowired
    public FundraiserController(FundraiserService fundraiserService) {
        this.fundraiserService = fundraiserService;
    }

    @Autowired
    public FileService fileService;

    @Autowired
    public AttachmentRepository attachmentRepository;

    @Autowired
    public FundraiserRepository fundraiserRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public CoverAttachmentRepository coverAttachmentRepository;

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

    @PostMapping(value="/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Fundraiser> createFundraiser(@ModelAttribute FundraiserRequest fundraiserRequest,@PathVariable Long id) throws UserException, GeneralSecurityException, IOException {
        //MultipartFile multipartFile = fundraiserRequest.getFile();
        //File file = new File(multipartFile.getOriginalFilename());
        //FileOutputStream fos = new FileOutputStream(file);
        //fos.write(multipartFile.getBytes());
        //fos.close();
        Fundraiser fundraiser = new Fundraiser();

        //BeanUtils.copyProperties(fundraiserRequest, fundraiser);
        fundraiser.setTitle(fundraiserRequest.getTitle());
        fundraiser.setCategory(fundraiserRequest.getCategory());
        fundraiser.setCountry(fundraiserRequest.getCountry());
        fundraiser.setCity(fundraiserRequest.getCity());
        fundraiser.setFirstName(fundraiserRequest.getFirstName());
        fundraiser.setLastName(fundraiserRequest.getLastName());
        fundraiser.setDescription(fundraiserRequest.getDescription());
        fundraiser.setRaisedAmount(0);
        fundraiser.setRequiredAmount(fundraiserRequest.getRequiredAmount());
        fundraiser.setCreatedAt(LocalDateTime.now());

        File temp = File.createTempFile("temp",null);
        MultipartFile coverphoto = fundraiserRequest.getCoverPhoto();
        coverphoto.transferTo(temp);
        CoverAttachment coverAttachment = fileService.uploadImageToDriveCover(temp);

        fundraiser.setCoverAttachment(coverAttachment);

        List<MultipartFile> file = fundraiserRequest.getFiles();
        List<Attachment> attachment = new ArrayList<>();
        for(MultipartFile i:file){
            File qwer = File.createTempFile("qwer",null);
            i.transferTo(qwer);
            Attachment attach = fileService.uploadImageToDrive(qwer);
            attachment.add(attach);
        }
        fundraiser.setAttachment(attachment);
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
        fundraiserRepository.save(fundraiser);
        for(Attachment i:attachment){
            i.setFundraiser(fundraiser);
            attachmentRepository.save(i);
        }
        coverAttachment.setFundraiser(fundraiser);
        coverAttachmentRepository.save(coverAttachment);
//        attachmentRepository.save(attachment);
        //Fundraiser createdFundraiser = fundraiserService.createFundraiser(fundraiserRequest,id);
        return new ResponseEntity<>(fundraiser, HttpStatus.CREATED);
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

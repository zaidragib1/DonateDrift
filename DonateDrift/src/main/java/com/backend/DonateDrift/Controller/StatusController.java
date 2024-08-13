package com.backend.DonateDrift.Controller;

import com.backend.DonateDrift.dtos.StatusRequest;

import com.backend.DonateDrift.entity.CoverAttachment;
import com.backend.DonateDrift.entity.Status;
import com.backend.DonateDrift.entity.StatusAttachment;
import com.backend.DonateDrift.exception.UserException;
import com.backend.DonateDrift.repository.StatusAttachmentRepository;
import com.backend.DonateDrift.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://donatedrifts.vercel.app", allowCredentials = "true")

@RequestMapping("/api/status")
public class StatusController {

    private final StatusService statusService;

    @Autowired
    private StatusAttachmentRepository statusAttachmentRepository;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping(value="/{fundraiserId}",consumes = {"multipart/mixed", "multipart/form-data"})
    public ResponseEntity<Status> addStatus(@PathVariable Long fundraiserId, @ModelAttribute StatusRequest statusRequest) throws UserException, GeneralSecurityException, IOException {
        Status status = statusService.addStatus(fundraiserId, statusRequest);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @GetMapping("/{statusId}")
    public ResponseEntity<Status> getStatusById(@PathVariable Long statusId) {
        Status status = statusService.getStatusById(statusId);
        return ResponseEntity.ok().body(status);
    }

    

    @PutMapping("/{statusId}")
    public ResponseEntity<Status> updateStatus(@PathVariable Long statusId, @RequestBody StatusRequest statusRequest) {
        Status updatedStatus = statusService.updateStatus(statusId, statusRequest);
        return ResponseEntity.ok().body(updatedStatus);
    }

    @DeleteMapping("/{statusId}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Long statusId) {
        statusService.deleteStatus(statusId);
        return ResponseEntity.noContent().build();
    }
}

package com.backend.DonateDrift.Controller;

import com.backend.DonateDrift.dtos.StatusRequest;
import com.backend.DonateDrift.entity.Status;
import com.backend.DonateDrift.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping("/{fundraiserId}")
    public ResponseEntity<Status> addStatus(@PathVariable Long fundraiserId, @RequestBody StatusRequest statusRequest) {
        Status status = statusService.addStatus(fundraiserId, statusRequest);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @GetMapping("/{statusId}")
    public ResponseEntity<Status> getStatusById(@PathVariable Long statusId) {
        Status status = statusService.getStatusById(statusId);
        return ResponseEntity.ok().body(status);
    }

    @GetMapping("/fundraiser/{fundraiserId}")
    public ResponseEntity<List<Status>> getStatusByFundraiserId(@PathVariable Long fundraiserId) {
        List<Status> statuses = statusService.getStatusByFundraiserId(fundraiserId);
        return ResponseEntity.ok().body(statuses);
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
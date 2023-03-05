package com.spring.vehiclerenting.controller;

import com.spring.vehiclerenting.dto.request.CreateApplication;
import com.spring.vehiclerenting.dto.request.DeleteApplication;
import com.spring.vehiclerenting.dto.request.UpdateApplicationDates;
import com.spring.vehiclerenting.dto.request.UpdateApplicationStatus;
import com.spring.vehiclerenting.dto.response.MessageResponse;
import com.spring.vehiclerenting.model.Application;
import com.spring.vehiclerenting.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

//    @GetMapping("/applications")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<?> listApplications(){
//        this.applicationService.listApplications();
//        return ResponseEntity.ok(new MessageResponse("List of all applications!"));
//    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Set<Application>> getAll() {
        return new ResponseEntity<>(applicationService.listApplications(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Set<Application>> filterByEndDate() {
        return new ResponseEntity<>(applicationService.filterByEndDate(), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createApplication(@Valid @RequestBody CreateApplication createApplication){
        this.applicationService.createApplication(createApplication.getVehicleId(), createApplication.getStartDate(), createApplication.getEndDate());
        return ResponseEntity.ok(new MessageResponse("Application added successfully!"));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteApplication(@Valid @RequestBody DeleteApplication deleteApplication){
        this.applicationService.deleteApplication(deleteApplication.getApplicationId());
        return ResponseEntity.ok(new MessageResponse("Application deleted successfully!"));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateApplication(@Valid @RequestBody UpdateApplicationDates updateApplicationDates){
        this.applicationService.updateApplicationDates(updateApplicationDates.getApplicationId(), updateApplicationDates.getStartDate(), updateApplicationDates.getEndDate());
        return ResponseEntity.ok(new MessageResponse("Application dates updated successfully!"));
    }

    @PutMapping("/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateApplicationStatus(@Valid @RequestBody UpdateApplicationStatus updateApplication){
        this.applicationService.updateApplicationStatus(updateApplication.getApplicationId(), updateApplication.getNewStatus());
        return ResponseEntity.ok(new MessageResponse("Application status updated successfully!"));
    }


}

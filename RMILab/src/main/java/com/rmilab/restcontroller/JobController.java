package com.rmilab.restcontroller;

import com.rmilab.dto.DockingForm;
import com.rmilab.entity.Job;
import com.rmilab.repository.JobRepository;
import com.rmilab.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class JobController {
    @Autowired
    private JobService jobService;

    @Autowired
    private JobRepository jobRepository;
    // constructor and other methods

    @PostMapping("/submit")
    public ResponseEntity<?> submit(@ModelAttribute DockingForm form) {
        System.out.println("Submit API called");
        Job job = new Job();
        // Populate the job with data from the form
        jobRepository.save(job);
       // jobService.startDocking(job);
        return new ResponseEntity<>(job, HttpStatus.CREATED);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<?> viewJob(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Job job = jobRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        return new ResponseEntity<>(job, HttpStatus.OK);
    }


}

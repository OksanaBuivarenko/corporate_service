package com.engineering_bureau.corporate_service.controller.emploee;

import com.engineering_bureau.corporate_service.dto.request.employee.JobTitleRq;
import com.engineering_bureau.corporate_service.dto.request.Marker;
import com.engineering_bureau.corporate_service.dto.response.employee.JobTitleRs;
import com.engineering_bureau.corporate_service.service.JobTitleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-title")
public class JobTitleController {

    private final JobTitleService jobTitleService;

    @Operation(description = "Getting all job title")
    @GetMapping()
    public List<JobTitleRs> getAllJobTitleRs() {
        return jobTitleService.getAllJobTitleRs();
    }

    @Operation(description = "Getting job title by id")
    @GetMapping("/{id}")
    public JobTitleRs getJobTitleRsById(@PathVariable Long id) {
        return jobTitleService.getJobTitleRsById(id);
    }

    @Validated({Marker.OnCreate.class})
    @Operation(description = "Create job title")
    @PostMapping()
    public JobTitleRs createJobTitle(@RequestBody @Valid JobTitleRq jobTitleRq) {
        return jobTitleService.createJobTitle(jobTitleRq);
    }

    @Operation(description = "Update job title by id")
    @PutMapping("/{id}")
    public JobTitleRs updateJobTitleById(@PathVariable Long id, @RequestBody @Valid JobTitleRq jobTitleRq) {
        return jobTitleService.updateJobTitleById(id, jobTitleRq);
    }

    @Operation(description = "Delete job title by id")
    @DeleteMapping("/{id}")
    public String deleteJobTitleById(@PathVariable Long id) {
        return jobTitleService.deleteJobTitleById(id);
    }
}
package com.engineering_bureau.corporate_service.service;

import com.engineering_bureau.corporate_service.dto.request.employee.JobTitleRq;
import com.engineering_bureau.corporate_service.dto.response.employee.JobTitleRs;
import com.engineering_bureau.corporate_service.entity.emploee.JobTitle;

import java.util.List;

public interface JobTitleService {

    JobTitle getJobTitleByTitle(String jobTitle);

    JobTitle getJobTitleById(Long id);

    List<JobTitleRs> getAllJobTitleRs();

    JobTitleRs getJobTitleRsById(Long id);

    JobTitleRs createJobTitle(JobTitleRq jobTitleRq);

    JobTitleRs updateJobTitleById(Long id, JobTitleRq jobTitleRq);

    String deleteJobTitleById(Long id);
}

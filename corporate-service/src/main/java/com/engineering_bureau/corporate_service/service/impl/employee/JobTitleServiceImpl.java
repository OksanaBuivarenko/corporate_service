package com.engineering_bureau.corporate_service.service.impl.employee;

import com.engineering_bureau.corporate_service.dto.request.employee.JobTitleRq;
import com.engineering_bureau.corporate_service.dto.response.employee.JobTitleRs;
import com.engineering_bureau.corporate_service.entity.emploee.JobTitle;
import com.engineering_bureau.corporate_service.exception.ObjectAlreadyExistsException;
import com.engineering_bureau.corporate_service.mapper.employee.JobTitleMapper;
import com.engineering_bureau.corporate_service.repository.employee.JobTitleRepository;
import com.engineering_bureau.corporate_service.service.JobTitleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobTitleServiceImpl implements JobTitleService {

    private final JobTitleRepository jobTitleRepository;

    private final JobTitleMapper jobTitleMapper;

    @Override
    public JobTitle getJobTitleByTitle(String jobTitle) {
        return jobTitleRepository.findByTitle(jobTitle).orElseThrow(() ->
                new EntityNotFoundException("Job title with title " + jobTitle + " not found"));
    }

    @Override
    public JobTitle getJobTitleById(Long id) {
        return jobTitleRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Job title with id " + id + " not found"));
    }

    @Override
    public List<JobTitleRs> getAllJobTitleRs() {
        return jobTitleRepository.findAll().stream().map(jobTitleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public JobTitleRs getJobTitleRsById(Long id) {
        return jobTitleMapper.toDto(getJobTitleById(id));
    }

    @Override
    public JobTitleRs createJobTitle(JobTitleRq jobTitleRq) {
        if(jobTitleRepository.existsByTitle(jobTitleRq.getTitle())) {
            throw  new ObjectAlreadyExistsException("Job title with title " + jobTitleRq.getTitle() + " already exists");
        }
        JobTitle jobTitle = jobTitleMapper.toEntity(jobTitleRq);
        JobTitle saveJobTitle = jobTitleRepository.save(jobTitle);
        return jobTitleMapper.toDto(saveJobTitle);
    }

    @Override
    public JobTitleRs updateJobTitleById(Long id, JobTitleRq jobTitleRq) {
        JobTitle jobTitle = getJobTitleById(id);
        jobTitle.setTitle(jobTitleRq.getTitle());
        JobTitle updateJobTitle = jobTitleRepository.save(jobTitle);
        return jobTitleMapper.toDto(updateJobTitle);
    }

    @Override
    public String deleteJobTitleById(Long id) {
        JobTitle jobTitle = getJobTitleById(id);
        jobTitleRepository.delete(jobTitle);
        String deleteMessage = "Job title with id " + id + "delete success";
        log.info(deleteMessage);
        return deleteMessage;
    }
}
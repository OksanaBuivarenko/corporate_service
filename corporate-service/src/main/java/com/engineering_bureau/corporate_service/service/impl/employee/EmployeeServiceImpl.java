package com.engineering_bureau.corporate_service.service.impl.employee;

import com.engineering_bureau.corporate_service.dto.PageRs;
import com.engineering_bureau.corporate_service.dto.request.employee.EmployeeRq;
import com.engineering_bureau.corporate_service.dto.request.employee.EmployeeSearchRq;
import com.engineering_bureau.corporate_service.dto.response.employee.EmployeeRs;
import com.engineering_bureau.corporate_service.entity.company.Company;
import com.engineering_bureau.corporate_service.entity.company.Company_;
import com.engineering_bureau.corporate_service.entity.emploee.Employee;
import com.engineering_bureau.corporate_service.entity.emploee.Employee_;
import com.engineering_bureau.corporate_service.entity.emploee.JobTitle;
import com.engineering_bureau.corporate_service.entity.emploee.JobTitle_;
import com.engineering_bureau.corporate_service.mapper.employee.EmployeeMapper;
import com.engineering_bureau.corporate_service.repository.employee.EmployeeRepository;
import com.engineering_bureau.corporate_service.repository.specification.Specs;
import com.engineering_bureau.corporate_service.service.CompanyService;
import com.engineering_bureau.corporate_service.service.EmployeeService;
import com.engineering_bureau.corporate_service.service.JobTitleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    private final CompanyService companyService;

    private final JobTitleService jobTitleService;

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Employee with id " + id + " not found"));
    }

    @Override
    public PageRs<List<EmployeeRs>> getAllEmployeeRs(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return PageRs.<List<EmployeeRs>>builder()
                .offset(offset)
                .limit(limit)
                .data(employeeRepository.findAll(nextPage).stream().map((employeeMapper::toDto))
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public PageRs<List<EmployeeRs>> getAllEmployeeRsByCompanyId(Long companyId, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return PageRs.<List<EmployeeRs>>builder()
                .offset(offset)
                .limit(limit)
                .data(employeeRepository.findAllByCompanyId(companyId, nextPage).stream().map((employeeMapper::toDto))
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public EmployeeRs getEmployeeRsById(Long id) {
        return employeeMapper.toDto(getEmployeeById(id));
    }

    @Override
    public EmployeeRs createEmployeeByCompanyId(Long companyId, EmployeeRq employeeRq) {
        Company company = companyService.getCompanyById(companyId);
        JobTitle jobTitle = jobTitleService.getJobTitleByTitle(employeeRq.getJobTitle());
        Employee employee = employeeMapper.toEntity(employeeRq, company, jobTitle);
        Employee saveEmployee = employeeRepository.save(employee);
        return employeeMapper.toDto(saveEmployee);
    }

    @Override
    public EmployeeRs updateEmployeeById(Long id, EmployeeRq employeeRq) {
        Employee employee = getEmployeeById(id);
        if (employeeRq.getFirstName()!=null && !employeeRq.getFirstName().trim().isEmpty()) {
            employee.setFirstName(employeeRq.getFirstName());
        }
        if (employeeRq.getLastName()!=null && !employeeRq.getLastName().trim().isEmpty()) {
            employee.setLastName(employeeRq.getLastName());
        }
        if (employeeRq.getPatronymic()!=null && !employeeRq.getPatronymic().trim().isEmpty()) {
            employee.setPatronymic(employeeRq.getPatronymic());
        }
        if (employeeRq.getJobTitle()!=null && !employeeRq.getJobTitle().trim().isEmpty()) {
            JobTitle jobTitle = jobTitleService.getJobTitleByTitle(employeeRq.getJobTitle());
            employee.setJobTitle(jobTitle);
        }
        Employee updateEmployee = employeeRepository.save(employee);
        return employeeMapper.toDto(updateEmployee);
    }

    @Override
    public String deleteEmployeeById(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
        String deleteMessage = "Employee with id " + id + "delete success";
        log.info(deleteMessage);
        return deleteMessage;
    }

    @Override
    public PageRs<List<EmployeeRs>> searchEmployeeRsByQuery(EmployeeSearchRq searchRq, Integer offset, Integer limit) {
        List<EmployeeRs> resultList =   employeeRepository.findAll(
                Specification.where(Specs.eq(Employee_.id, searchRq.getId()))
                        .and(Specs.like(Employee_.firstName, searchRq.getFirstName()))
                        .and(Specs.like(Employee_.lastName, searchRq.getLastName()))
                        .and(Specs.like(Employee_.patronymic, searchRq.getPatronymic()))
                        .and(Specs.like(Employee_.jobTitle, JobTitle_.title, searchRq.getJobTitle()))
                        .and(Specs.like(Employee_.company, Company_.name, searchRq.getCompany())),
                PageRequest.of(offset, limit))
                .stream().map((employeeMapper::toDto)).collect(Collectors.toList());

        return PageRs.<List<EmployeeRs>>builder()
                .offset(offset)
                .limit(limit)
                .data(resultList)
                .build();
    }
}
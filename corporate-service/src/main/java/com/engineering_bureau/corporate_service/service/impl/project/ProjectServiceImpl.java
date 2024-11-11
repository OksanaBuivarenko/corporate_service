package com.engineering_bureau.corporate_service.service.impl.project;

import com.engineering_bureau.corporate_service.dto.PageRs;
import com.engineering_bureau.corporate_service.dto.request.project.ProjectRq;
import com.engineering_bureau.corporate_service.dto.request.project.ProjectSearchRq;
import com.engineering_bureau.corporate_service.dto.response.project.ProjectRs;
import com.engineering_bureau.corporate_service.entity.company.Company;
import com.engineering_bureau.corporate_service.entity.company.Company_;
import com.engineering_bureau.corporate_service.entity.emploee.Employee;
import com.engineering_bureau.corporate_service.entity.emploee.Employee_;
import com.engineering_bureau.corporate_service.entity.project.Project;
import com.engineering_bureau.corporate_service.entity.project.Project_;
import com.engineering_bureau.corporate_service.mapper.project.ProjectMapper;
import com.engineering_bureau.corporate_service.repository.project.ProjectRepository;
import com.engineering_bureau.corporate_service.repository.specification.Specs;
import com.engineering_bureau.corporate_service.service.CompanyService;
import com.engineering_bureau.corporate_service.service.EmployeeService;
import com.engineering_bureau.corporate_service.service.ProjectService;
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
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final CompanyService companyService;
    private final EmployeeService employeeService;

    @Override
    public PageRs<List<ProjectRs>> getAllProjectRs(Integer offset,Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return PageRs.<List<ProjectRs>>builder()
                .offset(offset)
                .limit(limit)
                .data(projectRepository.findAll(nextPage).stream().map(projectMapper::toDto).collect(Collectors.toList()))
                .build();
    }

    @Override
    public ProjectRs getProjectRsById(Long id) {
        return projectMapper.toDto(getProjectById(id));
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Project with id " + id + " not found"));
    }

    @Override
    public ProjectRs createProject(ProjectRq projectRq) {
        Project project = projectMapper.toEntity(projectRq);
        Project saveProject = projectRepository.save(project);

        return projectMapper.toDto(saveProject);
    }

    @Override
    public ProjectRs updateProjectById(Long id, ProjectRq projectRq) {
        Project project = getProjectById(id);
        if (projectRq.getTitle()!=null && !projectRq.getTitle().trim().isEmpty()) {
            project.setTitle(projectRq.getTitle());
        }
        if (projectRq.getDescription()!=null && !projectRq.getDescription().trim().isEmpty()) {
            project.setDescription(projectRq.getDescription());
        }
        Project updateProject = projectRepository.save(project);

        return projectMapper.toDto(updateProject);
    }

    @Override
    public String deleteProjectById(Long id) {
        projectRepository.delete(getProjectById(id));
        String deleteMessage = "Project with id " + id + "delete success";
        log.info(deleteMessage);

        return deleteMessage;
    }

    @Override
    public ProjectRs addCompanyClient(Long id, Long clientId) {
        Project project = getProjectById(id);
        Company client = companyService.getCompanyById(id);
        project.setClient(client);

        return projectMapper.toDto(projectRepository.save(project));
    }

    @Override
    public ProjectRs addResponsibleEmployee(Long id, Long employeeId) {
        Project project = getProjectById(id);
        Employee responsible = employeeService.getEmployeeById(employeeId);
        project.setResponsible(responsible);

        return projectMapper.toDto(projectRepository.save(project));
    }

    @Override
    public PageRs<List<ProjectRs>> searchProjectRsByQuery(ProjectSearchRq searchRq, Integer offset, Integer limit) {
        List<ProjectRs> resultList =  projectRepository.findAll(
                Specification.where(Specs.eq(Project_.id, searchRq.getId()))
                        .and(Specs.like(Project_.title, searchRq.getTitle()))
                        .and(Specs.like(Project_.client, Company_.name, searchRq.getClient()))
                        .and(Specs.like(Project_.responsible, Employee_.lastName, searchRq.getResponsible()))
                        .and(Specs.like(Project_.description, searchRq.getDescription())),
                PageRequest.of(offset, limit))
                .stream().map(projectMapper::toDto).collect(Collectors.toList());

        return PageRs.<List<ProjectRs>>builder()
                .offset(offset)
                .limit(limit)
                .data(resultList)
                .build();
    }
}
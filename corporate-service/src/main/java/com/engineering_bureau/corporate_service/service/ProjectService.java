package com.engineering_bureau.corporate_service.service;

import com.engineering_bureau.corporate_service.dto.PageRs;
import com.engineering_bureau.corporate_service.dto.request.project.ProjectRq;
import com.engineering_bureau.corporate_service.dto.request.project.ProjectSearchRq;
import com.engineering_bureau.corporate_service.dto.response.project.ProjectRs;
import com.engineering_bureau.corporate_service.entity.project.Project;

import java.util.List;

public interface ProjectService {

    PageRs<List<ProjectRs>> getAllProjectRs(Integer offset, Integer limit);

    ProjectRs getProjectRsById(Long id);

    Project getProjectById(Long id);

    ProjectRs createProject(ProjectRq projectRq);

    ProjectRs updateProjectById(Long id, ProjectRq projectRq);

    String deleteProjectById(Long id);

    ProjectRs addCompanyClient(Long id, Long clientId);

    ProjectRs addResponsibleEmployee(Long id, Long employeeId);

    PageRs<List<ProjectRs>> searchProjectRsByQuery(ProjectSearchRq searchRq, Integer offset,Integer limit);
}

package com.engineering_bureau.corporate_service.service;

import com.engineering_bureau.corporate_service.dto.request.project.ProjectStageRq;
import com.engineering_bureau.corporate_service.dto.response.project.ProjectStageRs;
import com.engineering_bureau.corporate_service.entity.project.ProjectStages;

import java.util.List;

public interface ProjectStagesService {

    List<ProjectStageRs> getAllProjectStageByProjectIdRs(Long projectId);

    ProjectStageRs getProjectStageRsById(Long id);

    ProjectStages getProjectStageById(Long id);

    ProjectStageRs createProjectStageByProjectId(Long projectId, ProjectStageRq projectStageRq);

    ProjectStageRs updateProjectStagesById(Long id, ProjectStageRq projectStagesRq);

    String deleteProjectStagesById(Long id);
}

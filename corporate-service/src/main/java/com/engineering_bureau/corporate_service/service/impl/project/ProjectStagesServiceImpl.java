package com.engineering_bureau.corporate_service.service.impl.project;

import com.engineering_bureau.corporate_service.dto.request.project.ProjectStageRq;
import com.engineering_bureau.corporate_service.dto.response.project.ProjectStageRs;
import com.engineering_bureau.corporate_service.entity.project.Project;
import com.engineering_bureau.corporate_service.entity.project.ProjectStages;
import com.engineering_bureau.corporate_service.mapper.project.ProjectStagesMapper;
import com.engineering_bureau.corporate_service.repository.project.ProjectStagesRepository;
import com.engineering_bureau.corporate_service.service.ProjectStagesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectStagesServiceImpl implements ProjectStagesService {

    private final ProjectStagesRepository projectStagesRepository;

    private final ProjectStagesMapper projectStagesMapper;

    private final ProjectServiceImpl projectService;

    @Override
    public List<ProjectStageRs> getAllProjectStageByProjectIdRs(Long projectId) {
        return projectStagesRepository.findAllByProject_Id(projectId).stream()
                .map(projectStagesMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProjectStageRs getProjectStageRsById(Long id) {
        return projectStagesMapper.toDto(getProjectStageById(id));
    }

    @Override
    public ProjectStages getProjectStageById(Long id) {
        return projectStagesRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Project's stage with id " + id + " not found"));
    }

    @Override
    public ProjectStageRs createProjectStageByProjectId(Long projectId, ProjectStageRq projectStageRq) {
        Project project = projectService.getProjectById(projectId);
        ProjectStages projectStages = projectStagesMapper.toEntity(projectStageRq, project);
        ProjectStages saveProjectStage = projectStagesRepository.save(projectStages);

        return projectStagesMapper.toDto(saveProjectStage);
    }

    @Override
    public ProjectStageRs updateProjectStagesById(Long id, ProjectStageRq projectStagesRq) {
        ProjectStages projectStages = getProjectStageById(id);
        if (projectStagesRq.getName()!=null && !projectStagesRq.getName().trim().isEmpty()) {
            projectStages.setName(projectStagesRq.getName());
        }
        if (projectStagesRq.getDescription()!=null && !projectStagesRq.getDescription().trim().isEmpty()) {
            projectStages.setDescription(projectStagesRq.getDescription());
        }
        if (projectStagesRq.getStartStage()!=null) {
            projectStages.setStartStage(projectStagesRq.getStartStage());
        }
        if (projectStagesRq.getEndStage()!=null) {
            projectStages.setEndStage(projectStagesRq.getEndStage());
        }
        ProjectStages updateProjectStage = projectStagesRepository.save(projectStages);

        return projectStagesMapper.toDto(updateProjectStage);
    }

    @Override
    public String deleteProjectStagesById(Long id) {
        projectStagesRepository.delete(getProjectStageById(id));
        String deleteMessage = "Project's stage with id " + id + "delete success";
        log.info(deleteMessage);

        return deleteMessage;
    }
}
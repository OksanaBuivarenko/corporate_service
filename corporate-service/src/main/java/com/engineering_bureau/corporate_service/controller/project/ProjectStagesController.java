package com.engineering_bureau.corporate_service.controller.project;

import com.engineering_bureau.corporate_service.dto.request.Marker;
import com.engineering_bureau.corporate_service.dto.request.project.ProjectStageRq;
import com.engineering_bureau.corporate_service.dto.response.project.ProjectStageRs;
import com.engineering_bureau.corporate_service.service.ProjectStagesService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/project-stage")
public class ProjectStagesController {
    private final ProjectStagesService projectStagesService;

    @Operation(description = "Getting all projects stage by project id")
    @GetMapping("/project/{project_id}")
    public List<ProjectStageRs> getAllProjectStageByProjectIdRs(@PathVariable(name = "project_id")
                                                                            Long projectId) {
        return projectStagesService.getAllProjectStageByProjectIdRs(projectId);
    }

    @Operation(description = "Getting project stages by id")
    @GetMapping("/{id}")
    public ProjectStageRs getProjectStageRsById(@PathVariable Long id) {
        return projectStagesService.getProjectStageRsById(id);
    }

    @Validated({Marker.OnCreate.class})
    @Operation(description = "Create project stage by project id")
    @PostMapping("/project/{project_id}")
    public ProjectStageRs createProjectStageByProjectId(@PathVariable(name = "project_id") Long projectId,
                                                                @RequestBody @Valid ProjectStageRq projectStageRq) {
        return projectStagesService.createProjectStageByProjectId(projectId, projectStageRq);
    }

    @Operation(description = "Update project stages by id")
    @PutMapping("/{id}")
    public ProjectStageRs updateProjectStagesById(@PathVariable Long id,
                                                     @RequestBody @Valid ProjectStageRq projectStagesRq) {
        return projectStagesService.updateProjectStagesById(id, projectStagesRq);
    }

    @Operation(description = "Delete project stages")
    @DeleteMapping("/{id}")
    public String deleteProjectStagesById(@PathVariable Long id) {
        return projectStagesService.deleteProjectStagesById(id);
    }
}
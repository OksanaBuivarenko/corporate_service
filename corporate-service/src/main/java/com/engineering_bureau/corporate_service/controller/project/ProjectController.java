package com.engineering_bureau.corporate_service.controller.project;

import com.engineering_bureau.corporate_service.dto.PageRs;
import com.engineering_bureau.corporate_service.dto.request.Marker;
import com.engineering_bureau.corporate_service.dto.request.PageSizeRq;
import com.engineering_bureau.corporate_service.dto.request.project.ProjectRq;
import com.engineering_bureau.corporate_service.dto.request.project.ProjectSearchRq;
import com.engineering_bureau.corporate_service.dto.response.project.ProjectRs;
import com.engineering_bureau.corporate_service.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    @Operation(description = "Getting all projects")
    @GetMapping()
    public PageRs<List<ProjectRs>> getAllProjectRs(@ParameterObject @Valid PageSizeRq req) {
        return projectService.getAllProjectRs(req.getOffset(), req.getLimit());
    }

    @Operation(description = "Getting project by id")
    @GetMapping("/{id}")
    public ProjectRs getProjectRsById(@PathVariable Long id) {
        return projectService.getProjectRsById(id);
    }

    @Validated({Marker.OnCreate.class})
    @Operation(description = "Create project")
    @PostMapping()
    public ProjectRs createProject(@RequestBody @Valid ProjectRq projectRq) {
        return projectService.createProject(projectRq);
    }

    @Validated({Marker.OnUpdate.class})
    @Operation(description = "Update project by id")
    @PutMapping("/{id}")
    public ProjectRs updateProjectById(@PathVariable Long id, @RequestBody @Valid ProjectRq projectRq) {
        return projectService.updateProjectById(id, projectRq);
    }

    @Operation(description = "Delete project")
    @DeleteMapping("/{id}")
    public String deleteProjectById(@PathVariable Long id) {
        return projectService.deleteProjectById(id);
    }

    @Operation(description = "Add company client to project by id")
    @GetMapping("/{id}/client/{client_id}")
    public ProjectRs addCompanyClient(@PathVariable Long id, @PathVariable(name = "client_id") Long clientId) {
        return projectService.addCompanyClient(id, clientId);
    }

    @Operation(description = "Add responsible employee to project by id")
    @GetMapping("/{id}/employee/{employee_id}")
    public ProjectRs addResponsibleEmployee(@PathVariable Long id,
                                                    @PathVariable(name = "employee_id") Long employeeId) {
        return projectService.addResponsibleEmployee(id, employeeId);
    }

    @Operation(description = "Searching project by query")
    @GetMapping(value = "/search")
    public PageRs<List<ProjectRs>> searchProjectRsByQuery(@ParameterObject @Valid ProjectSearchRq searchRq,
                                                  @ParameterObject @Valid PageSizeRq req) {
        return projectService.searchProjectRsByQuery(searchRq, req.getOffset(), req.getLimit());
    }
}

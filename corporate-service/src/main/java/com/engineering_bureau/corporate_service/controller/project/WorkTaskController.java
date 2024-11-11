package com.engineering_bureau.corporate_service.controller.project;

import com.engineering_bureau.corporate_service.dto.request.Marker;
import com.engineering_bureau.corporate_service.dto.request.project.WorkTaskRq;
import com.engineering_bureau.corporate_service.dto.response.project.CalculateRequiredProductRs;
import com.engineering_bureau.corporate_service.dto.response.project.CalculateRequiredProductRsWithComponents;
import com.engineering_bureau.corporate_service.dto.response.project.WorkTaskRs;
import com.engineering_bureau.corporate_service.service.WorkTaskService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/work-task")
public class WorkTaskController {

    private final WorkTaskService workTaskService;

    @Operation(description = "Getting all work tasks")
    @GetMapping()
    public List<WorkTaskRs> getAllWorkTaskRs() {
        return workTaskService.getAllWorkTaskRs();
    }

    @Operation(description = "Getting all work tasks by stage id")
    @GetMapping("/stage/{stage_id}")
    public List<WorkTaskRs> getAllWorkTaskRsByStageId(@PathVariable(name = "stage_id") Long stageId) {
        return workTaskService.getAllWorkTaskRsByStageId(stageId);
    }

    @Operation(description = "Getting work task by id")
    @GetMapping("/{id}")
    public WorkTaskRs getWorkTaskRsById(@PathVariable Long id) {
        return workTaskService.getWorkTaskRsById(id);
    }

    @Operation(description = "Calculate need to do product by work task id")
    @GetMapping("/calculate/{id}")
    public List<CalculateRequiredProductRs> getCalculateRequiredProductRs(@PathVariable Long id) {
        return workTaskService.getCalculateRequiredProductRs(id);
    }

    @Operation(description = "Calculate need to do product by work task id")
    @GetMapping("/calculate-components/{id}")
    public List<CalculateRequiredProductRsWithComponents> getCalculateRequiredProductRsWithComponents(@PathVariable Long id) {
        return workTaskService.getCalculateRequiredProductRsWithComponents(id);
    }

    @Validated({Marker.OnCreate.class})
    @Operation(description = "Create work task")
    @PostMapping("/stage/{stage_id}")
    public WorkTaskRs createWorkTaskByStageId(@PathVariable(name = "stage_id") Long stageId,
                                                     @RequestBody @Valid WorkTaskRq workTaskRq) {
        return workTaskService.createWorkTaskByStageId(stageId, workTaskRq);
    }

    @Operation(description = "Update work task by id")
    @PutMapping("/{id}")
    public WorkTaskRs updateWorkTaskById(@PathVariable Long id, @RequestBody @Valid WorkTaskRq workTaskRq) {
        return workTaskService.updateWorkTaskById(id, workTaskRq);
    }

    @Operation(description = "Delete work task by id")
    @DeleteMapping("/{id}")
    public String deleteWorkTaskById(@PathVariable Long id) {
        return workTaskService.deleteWorkTaskById(id);
    }
}
package com.engineering_bureau.corporate_service.controller.project;

import com.engineering_bureau.corporate_service.dto.request.Marker;
import com.engineering_bureau.corporate_service.dto.request.project.RequiredProductRq;
import com.engineering_bureau.corporate_service.dto.response.project.RequiredProductRs;
import com.engineering_bureau.corporate_service.service.RequiredProductsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/required-products")
public class RequiredProductsController {

    private final RequiredProductsService requiredProductsService;

    @Operation(description = "Getting all required products")
    @GetMapping()
    public List<RequiredProductRs> getAllRequiredProductRs() {
        return requiredProductsService.getAllRequiredProductRs();
    }

    @Operation(description = "Getting required product by id")
    @GetMapping("/{id}")
    public RequiredProductRs getRequiredProductRsById(@PathVariable Long id) {
        return requiredProductsService.getRequiredProductRsById(id);
    }

    @Validated({Marker.OnCreate.class})
    @Operation(description = "Create required product")
    @PostMapping("/task/{task_id}")
    public RequiredProductRs createRequiredProductByTaskId(@PathVariable(name = "task_id") Long taskId,
                                                      @RequestBody @Valid RequiredProductRq requiredProductRq) {
        return requiredProductsService.createRequiredProductByStageId(taskId, requiredProductRq);
    }

    @Validated({Marker.OnUpdate.class})
    @Operation(description = "Update required product by id")
    @PutMapping("/{id}")
    public RequiredProductRs updateRequiredProductById(@PathVariable Long id,
                                                               @RequestBody @Valid RequiredProductRq requiredProductRq) {
        return requiredProductsService.updateRequiredProductById(id, requiredProductRq);
    }

    @Operation(description = "Delete required product by id")
    @DeleteMapping("/{id}")
    public String deleteRequiredProductById(@PathVariable Long id) {
        return requiredProductsService.deleteRequiredProductById(id);
    }
}
package com.engineering_bureau.corporate_service.controller.storage;

import com.engineering_bureau.corporate_service.dto.PageRs;
import com.engineering_bureau.corporate_service.dto.request.PageSizeRq;
import com.engineering_bureau.corporate_service.dto.request.storage.ComponentRq;
import com.engineering_bureau.corporate_service.dto.request.Marker;
import com.engineering_bureau.corporate_service.dto.response.storage.ComponentRs;
import com.engineering_bureau.corporate_service.service.ComponentService;
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
@RequestMapping("/api/v1/component")
public class ComponentController {

    private final ComponentService componentService;

    @Operation(description = "Getting all components")
    @GetMapping()
    public PageRs<List<ComponentRs>> getAllComponentRs(@ParameterObject @Valid PageSizeRq req) {
        return componentService.getAllComponentRs(req.getOffset(), req.getLimit());
    }

    @Operation(description = "Getting component by id")
    @GetMapping("/{id}")
    public ComponentRs getComponentRsById(@PathVariable Long id) {
        return componentService.getComponentRsById(id);
    }

    @Validated({Marker.OnCreate.class})
    @Operation(description = "Create component")
    @PostMapping()
    public ComponentRs createComponent(@RequestBody @Valid ComponentRq componentRq) {
        return componentService.createComponent(componentRq);
    }

    @Validated({Marker.OnUpdate.class})
    @Operation(description = "Update component by id")
    @PutMapping("/{id}")
    public ComponentRs updateComponentById(@PathVariable Long id, @RequestBody @Valid ComponentRq componentRq) {
        return componentService.updateCompanyById(id, componentRq);
    }

    @Operation(description = "Delete component")
    @DeleteMapping("/{id}")
    public String deleteComponentById(@PathVariable Long id) {
        return componentService.deleteComponentById(id);
    }
}

package com.engineering_bureau.corporate_service.controller.storage;

import com.engineering_bureau.corporate_service.dto.request.Marker;
import com.engineering_bureau.corporate_service.dto.request.storage.RequiredComponentRq;
import com.engineering_bureau.corporate_service.dto.response.storage.RequiredComponentRs;
import com.engineering_bureau.corporate_service.service.RequiredComponentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/required-components")
public class RequiredComponentController {

    private final RequiredComponentService requiredComponentService;

    @Operation(description = "Getting all required components")
    @GetMapping()
    public List<RequiredComponentRs> getAllRequiredComponentRs() {
        return requiredComponentService.getAllRequiredComponentRs();
    }

    @Operation(description = "Getting required component by id")
    @GetMapping("/{id}")
    public RequiredComponentRs getRequiredComponentRsById(@PathVariable Long id) {
        return requiredComponentService.getRequiredComponentRsById(id);
    }

    @Validated({Marker.OnCreate.class})
    @Operation(description = "Create required component")
    @PostMapping("/product/{product_id}")
    public RequiredComponentRs createRequiredComponentByProductId(@PathVariable(name = "product_id") Long productId,
                                                           @RequestBody @Valid RequiredComponentRq requiredComponentRq) {
        return requiredComponentService.createRequiredComponentByProductId(productId, requiredComponentRq);
    }

    @Validated({Marker.OnUpdate.class})
    @Operation(description = "Update required component by id")
    @PutMapping("/{id}")
    public RequiredComponentRs updateRequiredComponentById(@PathVariable Long id,
                                                       @RequestBody @Valid RequiredComponentRq requiredComponentRq) {
        return requiredComponentService.updateRequiredComponentById(id, requiredComponentRq);
    }

    @Operation(description = "Delete required component by id")
    @DeleteMapping("/{id}")
    public String deleteRequiredComponentById(@PathVariable Long id) {
        return requiredComponentService.deleteRequiredComponentById(id);
    }
}

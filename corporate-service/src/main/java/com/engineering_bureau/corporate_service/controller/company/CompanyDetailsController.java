package com.engineering_bureau.corporate_service.controller.company;

import com.engineering_bureau.corporate_service.dto.request.company.CompanyDetailsRq;
import com.engineering_bureau.corporate_service.dto.request.Marker;
import com.engineering_bureau.corporate_service.dto.response.company.CompanyDetailsRs;
import com.engineering_bureau.corporate_service.service.CompanyDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company/details")
public class CompanyDetailsController {

    private final CompanyDetailsService companyDetailsService;

    @Operation(description = "Getting all company details")
    @GetMapping()
    public List<CompanyDetailsRs> getAllCompanyDetailRs() {
        return companyDetailsService.getAllCompanyDetailRs();
    }

    @Operation(description = "Getting company details by id")
    @GetMapping("/{id}")
    public CompanyDetailsRs getCompanyDetailRsById(@PathVariable Long id) {
        return companyDetailsService.getCompanyDetailRsById(id);
    }

    @Validated({Marker.OnCreate.class})
    @Operation(description = "Create company details by company id")
    @PostMapping("/{id}")
    public CompanyDetailsRs createCompanyDetailsById(@PathVariable(name = "id")  Long companyId,
                                                                       @RequestBody @Valid CompanyDetailsRq detailsRq) {
        return companyDetailsService.createCompanyDetailsByCompanyId(companyId, detailsRq);
    }

    @Validated({Marker.OnUpdate.class})
    @Operation(description = "Update company details by company id")
    @PutMapping("/{id}")
    public CompanyDetailsRs updateCompanyDetailsById(@PathVariable Long id,
                                                                    @RequestBody @Valid CompanyDetailsRq detailsRq) {
        return companyDetailsService.updateCompanyDetailsById(id, detailsRq);
    }

    @Operation(description = "Delete company details by company id")
    @DeleteMapping("/{id}")
    public String deleteCompanyDetailById(@PathVariable Long id) {
        return companyDetailsService.deleteCompanyDetailRsById(id);
    }
}
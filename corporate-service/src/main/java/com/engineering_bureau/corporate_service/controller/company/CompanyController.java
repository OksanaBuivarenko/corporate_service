package com.engineering_bureau.corporate_service.controller.company;

import com.engineering_bureau.corporate_service.dto.PageRs;
import com.engineering_bureau.corporate_service.dto.request.company.CompanyRq;
import com.engineering_bureau.corporate_service.dto.request.company.CompanySearchRq;
import com.engineering_bureau.corporate_service.dto.request.Marker;
import com.engineering_bureau.corporate_service.dto.request.PageSizeRq;
import com.engineering_bureau.corporate_service.dto.response.company.CompanyRs;
import com.engineering_bureau.corporate_service.service.CompanyService;
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
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Operation(description = "Getting all companies")
    @GetMapping()
    public PageRs<List<CompanyRs>> getCompaniesRs(@ParameterObject @Valid PageSizeRq req) {
        return companyService.getCompaniesRs(req.getOffset(), req.getLimit());
    }

    @Operation(description = "Getting client's companies")
    @GetMapping(value = "/clients")
    public PageRs<List<CompanyRs>> getClientsCompaniesRs(@ParameterObject @Valid PageSizeRq req) {
        return companyService.getClientsCompaniesRs(req.getOffset(), req.getLimit());
    }

    @Operation(description = "Getting subcontractor's companies")
    @GetMapping(value = "/subcontractors")
    public PageRs<List<CompanyRs>> getSubcontractorsCompaniesRs(@ParameterObject @Valid PageSizeRq req) {
        return companyService.getSubcontractorsCompaniesRs(req.getOffset(), req.getLimit());
    }

    @Operation(description = "Getting our company")
    @GetMapping(value = "/owner")
    public CompanyRs getOwnerCompanyRs() {
        return companyService.getOwnerCompanyRs();
    }

    @Operation(description = "Getting company by id")
    @GetMapping(value = "/{id}")
    public CompanyRs getCompanyRsById(@PathVariable Long id) {
        return companyService.getCompanyRsById(id);
    }

    @Operation(description = "Searching company by query")
    @GetMapping(value = "/search")
    public PageRs<List<CompanyRs>> searchCompanyRsByQuery(@ParameterObject @Valid CompanySearchRq searchRq,
                                                          @ParameterObject @Valid PageSizeRq req) {
        return companyService.searchCompanyRsByQuery(searchRq, req.getOffset(), req.getLimit());
    }

    @Validated({Marker.OnCreate.class})
    @Operation(description = "Create company")
    @PostMapping()
    public CompanyRs createCompany(@RequestBody @Valid CompanyRq companyRq) {
        return companyService.createCompany(companyRq);
    }

    @Operation(description = "Update company by id")
    @PutMapping("/{id}")
    public CompanyRs updateCompany(@PathVariable Long id, @Valid @RequestBody CompanyRq companyRq) {
        return companyService.updateCompany(id, companyRq);
    }

    @Operation(description = "Delete company by id")
    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable Long id) {
        return companyService.deleteCompany(id);
    }
}
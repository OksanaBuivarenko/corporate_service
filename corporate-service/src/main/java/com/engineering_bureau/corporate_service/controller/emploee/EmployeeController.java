package com.engineering_bureau.corporate_service.controller.emploee;

import com.engineering_bureau.corporate_service.dto.PageRs;
import com.engineering_bureau.corporate_service.dto.request.PageSizeRq;
import com.engineering_bureau.corporate_service.dto.request.employee.EmployeeRq;
import com.engineering_bureau.corporate_service.dto.request.employee.EmployeeSearchRq;
import com.engineering_bureau.corporate_service.dto.request.Marker;
import com.engineering_bureau.corporate_service.dto.response.employee.EmployeeRs;
import com.engineering_bureau.corporate_service.service.EmployeeService;
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
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(description = "Getting all employees")
    @GetMapping()
    public PageRs<List<EmployeeRs>> getAllEmployeeRs(@ParameterObject @Valid PageSizeRq req) {
        return employeeService.getAllEmployeeRs(req.getOffset(), req.getLimit());
    }

    @Operation(description = "Getting all employees by company id id")
    @GetMapping("/company/{company_id}")
    public PageRs<List<EmployeeRs>> getAllEmployeeRsByCompanyId(@PathVariable(name = "company_id") Long companyId,
                                                                @ParameterObject @Valid PageSizeRq req) {
        return employeeService.getAllEmployeeRsByCompanyId(companyId, req.getOffset(), req.getLimit());
    }

    @Operation(description = "Getting employee by id")
    @GetMapping("/{id}")
    public EmployeeRs getEmployeeRsById(@PathVariable Long id) {
        return employeeService.getEmployeeRsById(id);
    }

    @Validated({Marker.OnCreate.class})
    @Operation(description = "Create employee")
    @PostMapping("/company/{company_id}")
    public EmployeeRs createEmployeeByCompanyId(@PathVariable(name = "company_id") Long companyId,
                                                      @RequestBody @Valid EmployeeRq employeeRq) {
        return employeeService.createEmployeeByCompanyId(companyId, employeeRq);
    }

    @Operation(description = "Update employee by id")
    @PutMapping("/{id}")
    public EmployeeRs updateEmployeeById(@PathVariable Long id, @RequestBody @Valid EmployeeRq employeeRq) {
        return employeeService.updateEmployeeById(id, employeeRq);
    }

    @Operation(description = "Delete employee by id")
    @DeleteMapping("/{id}")
    public String deleteEmployeeById(@PathVariable Long id) {
        return employeeService.deleteEmployeeById(id);
    }

    @Operation(description = "Searching employee by query")
    @GetMapping(value = "/search")
    public PageRs<List<EmployeeRs>> searchEmployeeRsByQuery(@ParameterObject @Valid EmployeeSearchRq searchRq,
                                                            @ParameterObject @Valid PageSizeRq req) {
        return employeeService.searchEmployeeRsByQuery(searchRq, req.getOffset(), req.getLimit());
    }
}
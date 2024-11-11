package com.engineering_bureau.corporate_service.dto.response.project;

import com.engineering_bureau.corporate_service.dto.response.company.CompanyRs;
import com.engineering_bureau.corporate_service.dto.response.employee.EmployeeRs;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProjectRs {
    @Schema(example = "4567")
    private Long id;

    @Schema(example = "BNT-1")
    private String title;

    @Schema(example = "Development of an automated device control system")
    private String description;

    private CompanyRs client;

    private EmployeeRs responsible;
}

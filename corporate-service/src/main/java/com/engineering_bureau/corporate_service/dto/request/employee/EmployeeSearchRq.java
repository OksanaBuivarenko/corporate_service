package com.engineering_bureau.corporate_service.dto.request.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EmployeeSearchRq {

    @Schema(description = "Employee id",example = "3456")
    @Positive
    private Long id;

    @Schema(description = "First name", example = "Ivan")
    private String firstName;

    @Schema(description = "Last name", example = "Ivanov")
    private String lastName;

    @Schema(description = "Patronymic", example = "Ivanovich")
    private String patronymic;

    @Schema(description = "Company name", example = "Technest")
    private String company;

    @Schema(description = "Job title", example = "Engineer")
    private String jobTitle;

//    @Schema(description = "Project title", example = "BNT-1")
//    private String project;
//
//    @Schema(description = "Work task title", example = "Write documentation")
//    private String task;
}

package com.engineering_bureau.corporate_service.dto.response.employee;

import com.engineering_bureau.corporate_service.entity.emploee.JobTitle;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EmployeeRs {

    @Schema(example = "4567")
    private Long id;

    @Schema(example = "Ivan")
    private String firstName;

    @Schema(example = "Ivanov")
    private String lastName;

    @Schema(example = "Ivanovich")
    private String patronymic;

    @Schema(example = "Engineer")
    private JobTitle jobTitle;
}

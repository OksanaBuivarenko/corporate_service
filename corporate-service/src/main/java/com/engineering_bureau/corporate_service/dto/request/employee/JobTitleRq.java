package com.engineering_bureau.corporate_service.dto.request.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JobTitleRq {

    @Schema(example = "Engineer")
    @NotBlank
    private String title;
}

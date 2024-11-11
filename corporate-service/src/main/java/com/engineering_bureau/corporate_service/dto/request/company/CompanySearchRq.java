package com.engineering_bureau.corporate_service.dto.request.company;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CompanySearchRq {

    @Schema(description = "Company id",example = "3456")
    @Positive
    private Long id;

    @Schema(description = "Company name", example = "Technest")
    private String name;

    @Schema(description = "Company INN", example = "7743013902")
    @Pattern(regexp = "\\d{10,12}")
    private String inn;
}

package com.engineering_bureau.corporate_service.dto.response.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class JobTitleRs {

    @Schema(example = "4567")
    private Long id;

    @Schema(example = "Engineer")
    private String title;
}

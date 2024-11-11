package com.engineering_bureau.corporate_service.dto.response.storage;

import com.engineering_bureau.corporate_service.dto.response.project.WorkTaskRs;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequiredComponentRs {
    @Schema(example = "4567")
    private Long id;

    private ProductRs product;

    @Schema(example = "100")
    private Integer count;

    private ComponentRs component;
}

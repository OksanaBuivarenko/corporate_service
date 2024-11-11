package com.engineering_bureau.corporate_service.dto.response.storage;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculateRequiredComponentRs {

    private ComponentRs component;

    @Schema(example = "150")
    @PositiveOrZero
    private Integer requiredComponentCount;

    @Schema(example = "100")
    @PositiveOrZero
    private Integer inStockComponentCount;

    @Schema(example = "50")
    private Integer outOfStockComponentCount;
}

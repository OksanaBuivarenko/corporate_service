package com.engineering_bureau.corporate_service.dto.response.project;

import com.engineering_bureau.corporate_service.dto.response.storage.ProductRs;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculateRequiredProductRs {

    private ProductRs product;

    @Schema(example = "150")
    @PositiveOrZero
    private Integer requiredProductCount;

    @Schema(example = "100")
    @PositiveOrZero
    private Integer doneProductCount;

    @Schema(example = "50")
    private Integer needToDoProductCount;
}

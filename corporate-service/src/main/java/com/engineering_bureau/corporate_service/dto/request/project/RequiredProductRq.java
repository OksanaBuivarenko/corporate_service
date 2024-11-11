package com.engineering_bureau.corporate_service.dto.request.project;

import com.engineering_bureau.corporate_service.dto.request.Marker;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class RequiredProductRq {

    @Schema(example = "control device")
    @NotBlank(groups = Marker.OnCreate.class)
    @Null(groups = Marker.OnUpdate.class)
    private String product;

    @Schema(example = "100")
    @PositiveOrZero
    @NotNull
    private Integer count;

}

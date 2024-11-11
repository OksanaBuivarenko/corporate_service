package com.engineering_bureau.corporate_service.dto.request.storage;

import com.engineering_bureau.corporate_service.dto.request.Marker;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class RequiredComponentRq {
    @Schema(example = "Сonnector")
    @NotBlank(groups = Marker.OnCreate.class)
    @Null(groups = Marker.OnUpdate.class)
    private String component;

    @Schema(example = "100")
    @PositiveOrZero
    @NotNull
    private Integer count;
}

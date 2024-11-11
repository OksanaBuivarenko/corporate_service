package com.engineering_bureau.corporate_service.dto.request.storage;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ComponentRq {

    @Schema(example = "Сonnector")
    @NotBlank
    private String name;

    @Schema(example = "Сonnector for cables")
    private String description;

    @Schema(example = "100")
    @PositiveOrZero
    private Integer count;

    @Schema(example = "Box 3")
    private String storagePlace;
}

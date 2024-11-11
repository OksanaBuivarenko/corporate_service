package com.engineering_bureau.corporate_service.dto.response.storage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ComponentRs {

    @Schema(example = "4567")
    private Long id;

    @Schema(example = "Сonnector")
    private String name;

    @Schema(example = "Сonnector for cables")
    private String description;

    @Schema(example = "100")
    private Integer count;

    @Schema(example = "Box 3")
    private String storagePlace;
}

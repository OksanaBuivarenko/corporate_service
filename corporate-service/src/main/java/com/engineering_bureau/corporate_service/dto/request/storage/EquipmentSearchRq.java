package com.engineering_bureau.corporate_service.dto.request.storage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EquipmentSearchRq {

    @Schema(example = "4567")
    private Long id;

    @Schema(example = "soldering-iron")
    private String name;

    @Schema(example = "electric soldering-iron")
    private String description;

    @Schema(example = "Box 3")
    private String storagePlace;
}

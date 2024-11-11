package com.engineering_bureau.corporate_service.dto.request.storage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EquipmentRq {

    @Schema(example = "soldering-iron")
    private String name;

    @Schema(example = "electric soldering-iron")
    private String description;

    @Schema(example = "2")
    private Integer count;

    @Schema(example = "Box 3")
    private String storagePlace;

}

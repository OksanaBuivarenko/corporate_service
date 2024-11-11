package com.engineering_bureau.corporate_service.dto.request.storage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProductSearchRq {

    @Schema(example = "4567")
    private Long id;

    @Schema(example = "control device")
    private String name;

    @Schema(example = "Box 3")
    private String storagePlace;
}

package com.engineering_bureau.corporate_service.dto.request.storage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class ProductRq {

    @Schema(example = "control device")
    private String name;

    @Schema(example = "KUPB device control")
    private String description;

    @Schema(example = "100")
    private Integer countFinishedProducts;

    @Schema(example = "Box 3")
    private String storagePlace;
}

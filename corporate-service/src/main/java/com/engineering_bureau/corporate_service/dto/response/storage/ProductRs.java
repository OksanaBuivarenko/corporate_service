package com.engineering_bureau.corporate_service.dto.response.storage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ProductRs {

    @Schema(example = "4567")
    private Long id;

    @Schema(example = "control device")
    private String name;

    @Schema(example = "KUPB device control")
    private String description;

    @Schema(example = "100")
    private Integer countFinishedProducts;

    @Schema(example = "Box 3")
    private String storagePlace;

    private List<ComponentRs> componentsList;
}

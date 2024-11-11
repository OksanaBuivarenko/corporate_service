package com.engineering_bureau.corporate_service.dto.request.project;

import com.engineering_bureau.corporate_service.dto.request.Marker;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectRq {

    @NotBlank(groups = Marker.OnCreate.class)
    @Schema(example = "BNT-1")
    private String title;

    @NotBlank(groups = Marker.OnCreate.class)
    @Schema(example = "Development of an automated device control system")
    private String description;
}
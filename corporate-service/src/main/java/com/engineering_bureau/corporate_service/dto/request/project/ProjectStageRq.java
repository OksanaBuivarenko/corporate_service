package com.engineering_bureau.corporate_service.dto.request.project;

import com.engineering_bureau.corporate_service.dto.request.Marker;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectStageRq {

    @Schema(description = "Project stage name", example = "Stage-1")
    @NotBlank(groups = Marker.OnCreate.class)
    private String name;

    @Schema(example = "Development of an automated device control system")
    @NotBlank(groups = Marker.OnCreate.class)
    private String description;

    @Schema(description = "Start stage time", example = "11-10-2024")
    @NotNull(groups = Marker.OnCreate.class)
    private LocalDateTime startStage;

    @Schema(description = "Start stage time", example = "11-12-2024")
    @NotNull(groups = Marker.OnCreate.class)
    private LocalDateTime endStage;
}

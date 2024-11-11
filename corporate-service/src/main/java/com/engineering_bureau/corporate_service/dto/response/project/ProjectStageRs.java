package com.engineering_bureau.corporate_service.dto.response.project;

import com.engineering_bureau.corporate_service.dto.response.project.ProjectRs;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectStageRs {
    @Schema(example = "4567")
    private Long id;

    @Schema(example = "Stage-1")
    private String name;

    @Schema(example = "Development of an automated device control system")
    private String description;

    @Schema(description = "Start stage time", example = "11-10-2024")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime startStage;

    @Schema(description = "End stage time", example = "11-12-2024")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime endStage;

    private ProjectRs project;
}

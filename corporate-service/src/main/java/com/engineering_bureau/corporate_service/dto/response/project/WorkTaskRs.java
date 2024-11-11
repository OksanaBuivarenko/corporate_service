package com.engineering_bureau.corporate_service.dto.response.project;

import com.engineering_bureau.corporate_service.dto.response.project.ProjectStageRs;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkTaskRs {

    @Schema(example = "4567")
    private Long id;

    @Schema(example = "Write documentation")
    private String title;

    @Schema(example = "Write documentation for this project")
    private String description;

    @Schema(description = "Start task time", example = "11-10-2024")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime startTask;

    @Schema(description = "End task time", example = "19-10-2024")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime endTask;

    private ProjectStageRs projectStage;
}

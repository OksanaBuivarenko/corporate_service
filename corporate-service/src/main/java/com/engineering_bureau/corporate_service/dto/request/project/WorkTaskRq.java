package com.engineering_bureau.corporate_service.dto.request.project;

import com.engineering_bureau.corporate_service.dto.request.Marker;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class WorkTaskRq {
    @NotBlank(groups = Marker.OnCreate.class)
    @Schema(example = "Write documentation")
    private String title;

    @NotBlank(groups = Marker.OnCreate.class)
    @Schema(example = "Write documentation for this project")
    private String description;

    @NotNull(groups = Marker.OnCreate.class)
    @Schema(description = "Start task time", example = "11-10-2024")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime startTask;

    @NotBlank(groups = Marker.OnCreate.class)
    @Schema(description = "End task time", example = "19-10-2024")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime endTask;
}

package com.engineering_bureau.corporate_service.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorRs {

    @Schema(description = "Error code", example = "404")
    private String code;

    @Schema(description = "Error message", example = "Company not found")
    private String errorMessage;
}
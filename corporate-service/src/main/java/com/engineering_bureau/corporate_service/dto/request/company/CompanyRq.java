package com.engineering_bureau.corporate_service.dto.request.company;

import com.engineering_bureau.corporate_service.dto.request.Marker;
import com.engineering_bureau.corporate_service.enumeration.CompanyType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyRq {

    @Schema(description = "Company name", example = "Technest")
    @NotBlank(groups = Marker.OnCreate.class)
    private String name;

    @Schema(description = "Company type enum: OWNER, CLIENT, SUBCONTRACTORS", example = "client")
    private CompanyType companyType;
}
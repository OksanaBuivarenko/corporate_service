package com.engineering_bureau.corporate_service.dto.response.company;

import com.engineering_bureau.corporate_service.enumeration.CompanyType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CompanyRs {

    @Schema(example = "4567")
    private Long id;

    @Schema(example = "Technest")
    private String name;

    @Schema(example = "client")
    private CompanyType companyType;

}

package com.engineering_bureau.corporate_service.dto.response.company;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CompanyDetailsRs {
    @Schema( example = "4567")
    private Long id;

    @Schema(example = "Limited liability company Technest")
    private String fullCompanyName;

    @Schema(example = "Russia Vyborg ul. Kosmonavtov 35-11 112233")
    private String postalAddress;

    @Schema(example = "Russia Vyborg ul. Kosmonavtov 35-11 112233")
    private String actualAddress;

    @Schema(example = "7743013902")
    private String inn;

    @Schema(example = "1172468024068")
    private String ogrn;

    @Schema(example = "773301001")
    private String kpp;

    @Schema(example = "40702810680060657001")
    private String bankAccount;

    @Schema(example = "PAO Sberbank")
    private String bank;

    @Schema(example = "40702810680060657001")
    private String correspondentAccount;

    @Schema(example = "7743013902")
    private String bankInn;

    @Schema(example = "773301001")
    private String bic;
}

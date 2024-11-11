package com.engineering_bureau.corporate_service.dto.request.company;

import com.engineering_bureau.corporate_service.dto.request.Marker;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CompanyDetailsRq {

    @Schema(description = "Full company name", example = "Limited liability company Technest")
    private String fullCompanyName;

    @Schema(description = "Company postal address", example = "Russia Vyborg ul. Kosmonavtov 35-11 112233")
    private String postalAddress;

    @Schema(description = "Company actual address", example = "Russia Vyborg ul. Kosmonavtov 35-11 112233")
    private String actualAddress;

    @Schema(description = "Company INN", example = "7743013902")
    @Pattern(regexp = "\\d{10,12}")
    private String inn;

    @Schema(description = "Company OGRN", example = "1172468024068")
    @Pattern(regexp = "\\d{13}")
    private String ogrn;

    @Schema(description = "Company KPP", example = "773301001")
    @Pattern(regexp = "\\d{9}")
    private String kpp;

    @Schema(description = "Company bank account", example = "40702810680060657001")
    @Pattern(regexp = "\\d{20}")
    private String bankAccount;

    @Schema(description = "Company bank name", example = "T-bank")
    @NotEmpty(groups = Marker.OnCreate.class)
    private String bank;

    @Schema(description = "Company correspondent account", example = "12345678901234567890")
    @Pattern(regexp = "\\d{20}")
    private String correspondentAccount;

    @Schema(description = "Bank INN", example = "7743013902")
    @Pattern(regexp = "\\d{10}")
    private String bankInn;

    @Schema(description = "BIC", example = "773301001")
    @Pattern(regexp = "\\d{9}")
    private String bic;
}

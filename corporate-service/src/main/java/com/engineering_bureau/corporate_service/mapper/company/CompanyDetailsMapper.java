package com.engineering_bureau.corporate_service.mapper.company;

import com.engineering_bureau.corporate_service.dto.request.company.CompanyDetailsRq;
import com.engineering_bureau.corporate_service.dto.response.company.CompanyDetailsRs;
import com.engineering_bureau.corporate_service.entity.company.CompanyDetails;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CompanyDetailsMapper {

    CompanyDetailsRs toDto(CompanyDetails details);

    //    @Mapping(target = "actualAddress", source = "actualAddress")
//    @Mapping(target = "bank", source = "bank")
//    @Mapping(target = "bankAccount", source = "bankAccount")
//    @Mapping(target = "bankInn", source = "bankInn")
//    @Mapping(target = "bic", source = "bic")
//    @Mapping(target = "correspondentAccount", source = "correspondentAccount")
//    @Mapping(target = "fullCompanyName", source = "fullCompanyName")
//    @Mapping(target = "inn", source = "inn")
//    @Mapping(target = "kpp", source = "kpp")
//    @Mapping(target = "ogrn", source = "ogrn")
//    @Mapping(target = "postalAddress", source = "postalAddress")
    CompanyDetails toCompanyDetail(CompanyDetailsRq detailsRq);
}

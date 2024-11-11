package com.engineering_bureau.corporate_service.mapper.company;

import com.engineering_bureau.corporate_service.dto.request.company.CompanyRq;
import com.engineering_bureau.corporate_service.dto.response.company.CompanyRs;
import com.engineering_bureau.corporate_service.entity.company.Company;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CompanyMapper {

    CompanyRs toDto(Company company);

    Company toCompanyEntity(CompanyRq companyRq);
}

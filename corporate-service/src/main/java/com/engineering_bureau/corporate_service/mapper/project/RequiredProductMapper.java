package com.engineering_bureau.corporate_service.mapper.project;

import com.engineering_bureau.corporate_service.dto.request.project.RequiredProductRq;
import com.engineering_bureau.corporate_service.dto.response.project.RequiredProductRs;
import com.engineering_bureau.corporate_service.entity.project.RequiredProduct;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RequiredProductMapper {

    RequiredProductRs toDto(RequiredProduct requiredProduct);

}
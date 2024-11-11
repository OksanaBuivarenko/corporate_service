package com.engineering_bureau.corporate_service.mapper.storage;

import com.engineering_bureau.corporate_service.dto.request.storage.RequiredComponentRq;
import com.engineering_bureau.corporate_service.dto.response.storage.RequiredComponentRs;
import com.engineering_bureau.corporate_service.entity.storage.RequiredComponent;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RequiredComponentMapper {

    RequiredComponentRs toDto(RequiredComponent requiredComponent);

}

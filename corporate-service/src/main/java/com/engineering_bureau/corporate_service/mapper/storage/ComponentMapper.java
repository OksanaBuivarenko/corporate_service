package com.engineering_bureau.corporate_service.mapper.storage;

import com.engineering_bureau.corporate_service.dto.request.storage.ComponentRq;
import com.engineering_bureau.corporate_service.dto.response.storage.ComponentRs;
import com.engineering_bureau.corporate_service.entity.storage.Component;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ComponentMapper {

    ComponentRs toDto(Component component);

    Component toEntity(ComponentRq componentRq);
}

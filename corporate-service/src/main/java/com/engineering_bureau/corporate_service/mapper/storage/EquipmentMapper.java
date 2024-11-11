package com.engineering_bureau.corporate_service.mapper.storage;

import com.engineering_bureau.corporate_service.dto.request.storage.EquipmentRq;
import com.engineering_bureau.corporate_service.dto.response.storage.EquipmentRs;
import com.engineering_bureau.corporate_service.entity.storage.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EquipmentMapper {

    EquipmentRs toDto(Equipment equipment);

    Equipment toEntity(EquipmentRq equipmentRq);
}

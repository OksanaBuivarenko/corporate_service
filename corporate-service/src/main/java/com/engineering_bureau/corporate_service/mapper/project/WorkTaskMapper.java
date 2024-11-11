package com.engineering_bureau.corporate_service.mapper.project;

import com.engineering_bureau.corporate_service.dto.request.project.WorkTaskRq;
import com.engineering_bureau.corporate_service.dto.response.project.WorkTaskRs;
import com.engineering_bureau.corporate_service.entity.project.ProjectStages;
import com.engineering_bureau.corporate_service.entity.project.WorkTask;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WorkTaskMapper {

    WorkTaskRs toDto(WorkTask workTask);

    @Mapping(target = "endTask", source = "workTaskRq.endTask")
    @Mapping(target = "startTask", source = "workTaskRq.startTask")
    @Mapping(target = "description", source = "workTaskRq.title")
    @Mapping(target = "title", source = "workTaskRq.title")
    @Mapping(target = "projectStage", source = "projectStage")
    WorkTask toEntity(WorkTaskRq workTaskRq, ProjectStages projectStage);
}

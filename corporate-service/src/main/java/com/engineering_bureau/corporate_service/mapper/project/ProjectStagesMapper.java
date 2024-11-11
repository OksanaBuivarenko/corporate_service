package com.engineering_bureau.corporate_service.mapper.project;


import com.engineering_bureau.corporate_service.dto.request.project.ProjectStageRq;
import com.engineering_bureau.corporate_service.dto.response.project.ProjectStageRs;
import com.engineering_bureau.corporate_service.entity.project.Project;
import com.engineering_bureau.corporate_service.entity.project.ProjectStages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProjectStagesMapper {

    ProjectStageRs toDto(ProjectStages projectStages);

    @Mapping(target = "project", source = "project")
    @Mapping(target = "endStage", source = "projectStageRq.endStage")
    @Mapping(target = "startStage", source = "projectStageRq.startStage")
    @Mapping(target = "description", source = "projectStageRq.description")
    @Mapping(target = "name", source = "projectStageRq.name")
    ProjectStages toEntity(ProjectStageRq projectStageRq, Project project);
}

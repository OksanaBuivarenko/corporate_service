package com.engineering_bureau.corporate_service.mapper.project;

import com.engineering_bureau.corporate_service.dto.request.project.ProjectRq;
import com.engineering_bureau.corporate_service.dto.response.project.ProjectRs;
import com.engineering_bureau.corporate_service.entity.project.Project;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProjectMapper {

    ProjectRs toDto(Project project);

    Project toEntity(ProjectRq projectRq);
}

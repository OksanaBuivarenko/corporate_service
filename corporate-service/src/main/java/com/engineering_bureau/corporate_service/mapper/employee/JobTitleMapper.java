package com.engineering_bureau.corporate_service.mapper.employee;

import com.engineering_bureau.corporate_service.dto.request.employee.JobTitleRq;
import com.engineering_bureau.corporate_service.dto.response.employee.JobTitleRs;
import com.engineering_bureau.corporate_service.entity.emploee.JobTitle;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface JobTitleMapper {

    JobTitleRs toDto(JobTitle jobTitle);

    JobTitle toEntity(JobTitleRq jobTitleRq);
}

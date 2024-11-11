package com.engineering_bureau.corporate_service.mapper.employee;

import com.engineering_bureau.corporate_service.dto.request.employee.EmployeeRq;
import com.engineering_bureau.corporate_service.dto.response.employee.EmployeeRs;
import com.engineering_bureau.corporate_service.entity.company.Company;
import com.engineering_bureau.corporate_service.entity.emploee.Employee;
import com.engineering_bureau.corporate_service.entity.emploee.JobTitle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {

    EmployeeRs toDto(Employee employee);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "company", source = "company")
    @Mapping(target = "jobTitle", source = "jobTitle")
    @Mapping(target = "patronymic", source = "employeeRq.patronymic")
    @Mapping(target = "lastName", source = "employeeRq.lastName")
    @Mapping(target = "firstName", source = "employeeRq.firstName")
    Employee toEntity(EmployeeRq employeeRq, Company company, JobTitle jobTitle);
}
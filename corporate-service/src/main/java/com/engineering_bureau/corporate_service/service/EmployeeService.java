package com.engineering_bureau.corporate_service.service;

import com.engineering_bureau.corporate_service.dto.PageRs;
import com.engineering_bureau.corporate_service.dto.request.employee.EmployeeRq;
import com.engineering_bureau.corporate_service.dto.request.employee.EmployeeSearchRq;
import com.engineering_bureau.corporate_service.dto.response.employee.EmployeeRs;
import com.engineering_bureau.corporate_service.entity.emploee.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeById(Long employeeId);

    PageRs<List<EmployeeRs>> getAllEmployeeRs(Integer offset, Integer limit);

    PageRs<List<EmployeeRs>> getAllEmployeeRsByCompanyId(Long companyId, Integer offset, Integer limit);

    EmployeeRs getEmployeeRsById(Long id);

    EmployeeRs createEmployeeByCompanyId(Long companyId, EmployeeRq employeeRq);

    EmployeeRs updateEmployeeById(Long id, EmployeeRq employeeRq);

    String deleteEmployeeById(Long id);

    PageRs<List<EmployeeRs>> searchEmployeeRsByQuery(EmployeeSearchRq searchRq, Integer offset, Integer limit);
}

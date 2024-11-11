package com.engineering_bureau.corporate_service.repository.employee;

import com.engineering_bureau.corporate_service.entity.emploee.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

   List<Employee> findAllByCompanyId(Long companyId, Pageable nextPage);
}

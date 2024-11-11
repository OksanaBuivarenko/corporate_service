package com.engineering_bureau.corporate_service.repository.company;

import com.engineering_bureau.corporate_service.enumeration.CompanyType;
import com.engineering_bureau.corporate_service.entity.company.Company;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {

    List<Company> findByCompanyType(CompanyType type, Pageable nextPage);
}

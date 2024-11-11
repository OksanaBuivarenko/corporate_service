package com.engineering_bureau.corporate_service.repository.company;

import com.engineering_bureau.corporate_service.entity.company.CompanyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompanyDetailsRepository extends JpaRepository<CompanyDetails, Long>,
        JpaSpecificationExecutor<CompanyDetails> {
}

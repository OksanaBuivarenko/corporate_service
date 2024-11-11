package com.engineering_bureau.corporate_service.service;

import com.engineering_bureau.corporate_service.dto.request.company.CompanyDetailsRq;
import com.engineering_bureau.corporate_service.dto.response.company.CompanyDetailsRs;

import java.util.List;

public interface CompanyDetailsService {

    List<CompanyDetailsRs> getAllCompanyDetailRs();

    CompanyDetailsRs getCompanyDetailRsById(Long id);

    CompanyDetailsRs createCompanyDetailsByCompanyId(Long companyId, CompanyDetailsRq detailsRq);

    CompanyDetailsRs updateCompanyDetailsById(Long id, CompanyDetailsRq detailsRq);

    String deleteCompanyDetailRsById(Long id);
}

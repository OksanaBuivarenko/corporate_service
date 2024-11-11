package com.engineering_bureau.corporate_service.service;

import com.engineering_bureau.corporate_service.dto.PageRs;
import com.engineering_bureau.corporate_service.dto.request.company.CompanyRq;
import com.engineering_bureau.corporate_service.dto.request.company.CompanySearchRq;
import com.engineering_bureau.corporate_service.dto.response.company.CompanyRs;
import com.engineering_bureau.corporate_service.entity.company.Company;
import com.engineering_bureau.corporate_service.entity.company.CompanyDetails;

import java.util.List;

public interface CompanyService {

    PageRs<List<CompanyRs>> getCompaniesRs(Integer offset, Integer limit);

    PageRs<List<CompanyRs>> getClientsCompaniesRs(Integer offset, Integer limit);

    PageRs<List<CompanyRs>> getSubcontractorsCompaniesRs(Integer offset, Integer limit);

    CompanyRs getOwnerCompanyRs();

    CompanyRs getCompanyRsById(Long id);

    PageRs<List<CompanyRs>> searchCompanyRsByQuery(CompanySearchRq searchRq, Integer offset, Integer limit);

    CompanyRs createCompany(CompanyRq companyRq);

    CompanyRs updateCompany(Long id, CompanyRq companyRq);

    String deleteCompany(Long id);

    void addDetails(Long companyId, CompanyDetails saveDetails);

    Company getCompanyById(Long id);
}

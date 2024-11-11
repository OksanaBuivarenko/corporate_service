package com.engineering_bureau.corporate_service.service.impl.company;

import com.engineering_bureau.corporate_service.dto.PageRs;
import com.engineering_bureau.corporate_service.entity.company.CompanyDetails;
import com.engineering_bureau.corporate_service.entity.company.CompanyDetails_;
import com.engineering_bureau.corporate_service.entity.company.Company_;
import com.engineering_bureau.corporate_service.enumeration.CompanyType;
import com.engineering_bureau.corporate_service.dto.request.company.CompanyRq;
import com.engineering_bureau.corporate_service.dto.request.company.CompanySearchRq;
import com.engineering_bureau.corporate_service.dto.response.company.CompanyRs;
import com.engineering_bureau.corporate_service.entity.company.Company;
import com.engineering_bureau.corporate_service.mapper.company.CompanyMapper;
import com.engineering_bureau.corporate_service.repository.company.CompanyRepository;
import com.engineering_bureau.corporate_service.repository.specification.Specs;
import com.engineering_bureau.corporate_service.service.CompanyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    @Override
    public PageRs<List<CompanyRs>> getCompaniesRs(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return PageRs.<List<CompanyRs>>builder()
                        .offset(offset)
                        .limit(limit)
                        .data(companyRepository.findAll(nextPage).stream().map((companyMapper::toDto))
                                .collect(Collectors.toList()))
                .build();
    }

    @Override
    public PageRs<List<CompanyRs>> getClientsCompaniesRs(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return PageRs.<List<CompanyRs>>builder()
                .offset(offset)
                .limit(limit)
                .data(companyRepository.findByCompanyType(CompanyType.CLIENT, nextPage).stream()
                        .map((companyMapper::toDto)).collect(Collectors.toList()))
                .build();
    }

    @Override
    public PageRs<List<CompanyRs>> getSubcontractorsCompaniesRs(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return PageRs.<List<CompanyRs>>builder()
                .offset(offset)
                .limit(limit)
                .data(companyRepository.findByCompanyType(CompanyType.SUBCONTRACTORS, nextPage).stream()
                        .map((companyMapper::toDto)).collect(Collectors.toList()))
                .build();
    }

    @Override
    public CompanyRs getOwnerCompanyRs() {
        Pageable nextPage = PageRequest.of(0, 1);
        return companyMapper.toDto(companyRepository.findByCompanyType(CompanyType.OWNER, nextPage).getFirst());
    }

    @Override
    public CompanyRs getCompanyRsById(Long id) {
        return companyMapper.toDto(getCompanyById(id));
    }

    @Override
    public PageRs<List<CompanyRs>> searchCompanyRsByQuery(CompanySearchRq searchRq, Integer offset, Integer limit) {
        List<CompanyRs> resultList =  companyRepository.findAll(
                Specification.where(Specs.eq(Company_.id, searchRq.getId()))
                        .and(Specs.eq(Company_.name, searchRq.getName()))
                        .and(Specs.eq(Company_.details, CompanyDetails_.inn, searchRq.getInn())),
                PageRequest.of(offset, limit))
                .stream().map(companyMapper::toDto).collect(Collectors.toList());

        return PageRs.<List<CompanyRs>>builder()
                .offset(offset)
                .limit(limit)
                .data(resultList)
                .build();
    }

    @Override
    public CompanyRs createCompany(CompanyRq companyRq) {
        Company company = companyMapper.toCompanyEntity(companyRq);
        Company saveCompany = companyRepository.save(company);

        return companyMapper.toDto(saveCompany);
    }

    @Override
    public CompanyRs updateCompany(Long id, CompanyRq companyRq) {
        Company company = getCompanyById(id);
        if (companyRq.getName()!=null && !companyRq.getName().trim().isEmpty()) {
            company.setName(companyRq.getName());
        }
        if (companyRq.getCompanyType()!=null) {
            company.setCompanyType(companyRq.getCompanyType());
        }
        Company updateCompany = companyRepository.save(company);
        return companyMapper.toDto(updateCompany);
    }

    @Override
    public String deleteCompany(Long id) {
         companyRepository.delete(getCompanyById(id));
         String deleteMessage = "Company with id " + id + "delete success";
         log.info(deleteMessage);
         return deleteMessage;
    }

    @Override
    public void addDetails(Long id, CompanyDetails details) {
        Company company = getCompanyById(id);
        company.setDetails(details);
        companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Company with id " + id + " not found"));
    }

}
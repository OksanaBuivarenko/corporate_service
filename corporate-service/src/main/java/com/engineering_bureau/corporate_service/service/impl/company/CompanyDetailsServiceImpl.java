package com.engineering_bureau.corporate_service.service.impl.company;

import com.engineering_bureau.corporate_service.dto.request.company.CompanyDetailsRq;
import com.engineering_bureau.corporate_service.dto.response.company.CompanyDetailsRs;
import com.engineering_bureau.corporate_service.entity.company.CompanyDetails;
import com.engineering_bureau.corporate_service.mapper.company.CompanyDetailsMapper;
import com.engineering_bureau.corporate_service.repository.company.CompanyDetailsRepository;
import com.engineering_bureau.corporate_service.service.CompanyDetailsService;
import com.engineering_bureau.corporate_service.service.CompanyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyDetailsServiceImpl implements CompanyDetailsService {

    private final CompanyDetailsRepository detailsRepository;

    private final CompanyDetailsMapper detailsMapper;

    private final CompanyService companyService;

    public CompanyDetails getDetailsById(Long id) {
        return detailsRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Company details with id " + id + " not found"));
    }

    @Override
    public List<CompanyDetailsRs> getAllCompanyDetailRs() {
        return detailsRepository.findAll().stream().map(detailsMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CompanyDetailsRs getCompanyDetailRsById(Long id) {
        return detailsMapper.toDto(getDetailsById(id));
    }

    @Override
    public CompanyDetailsRs createCompanyDetailsByCompanyId(Long companyId, CompanyDetailsRq detailsRq) {
        CompanyDetails details = detailsMapper.toCompanyDetail(detailsRq);
        CompanyDetails saveDetails = detailsRepository.save(details);
        companyService.addDetails(companyId, saveDetails);

        return detailsMapper.toDto(details);
    }

    @Override
    public CompanyDetailsRs updateCompanyDetailsById(Long id, CompanyDetailsRq detailsRq) {
        CompanyDetails details = getDetailsById(id);

        if (detailsRq.getFullCompanyName()!=null) {
            details.setFullCompanyName(details.getFullCompanyName());
        }
        if (detailsRq.getPostalAddress()!=null) {
            details.setPostalAddress(detailsRq.getPostalAddress());
        }
        if (detailsRq.getActualAddress()!=null) {
            details.setActualAddress(detailsRq.getActualAddress());
        }
        if (detailsRq.getInn()!=null) {
            details.setInn(detailsRq.getInn());
        }
        if (detailsRq.getOgrn()!=null) {
            details.setOgrn(detailsRq.getOgrn());
        }
        if (detailsRq.getKpp()!=null) {
            details.setKpp(detailsRq.getKpp());
        }
        if (detailsRq.getBankAccount()!=null) {
            details.setBankAccount(detailsRq.getBankAccount());
        }
        if (detailsRq.getBank()!=null) {
           details.setBank(detailsRq.getBank());
        }
        if (detailsRq.getCorrespondentAccount()!=null) {
            details.setCorrespondentAccount(detailsRq.getCorrespondentAccount());
        }
        if (detailsRq.getBankInn()!=null) {
            details.setBankInn(detailsRq.getBankInn());
        }
        if (detailsRq.getBic()!=null) {
            details.setBic(detailsRq.getBic());
        }
        CompanyDetails updateDetails = detailsRepository.save(details);

        return detailsMapper.toDto(updateDetails);
    }

    @Override
    public String deleteCompanyDetailRsById(Long id) {
        detailsRepository.delete(getDetailsById(id));
        String deleteMessage = "Company details with id " + id + " delete success";
        log.info(deleteMessage);

        return deleteMessage;
    }
}
package com.engineering_bureau.corporate_service.entity.company;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "company_details")
public class CompanyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "full_company_name")
    private String fullCompanyName;

    @Column(name = "postal_address")
    private String postalAddress;

    @Column(name = "actual_address")
    private String actualAddress;

    private String inn;

    private String ogrn;

    private String kpp;

    @Column(name = "bank_account")
    private String bankAccount;

    private String bank;

    @Column(name = "correspondent_account")
    private String correspondentAccount;

    @Column(name = "bank_inn")
    private String bankInn;

    private String bic;

    @OneToOne(mappedBy = "details", fetch = FetchType.LAZY)
    private Company company;
}
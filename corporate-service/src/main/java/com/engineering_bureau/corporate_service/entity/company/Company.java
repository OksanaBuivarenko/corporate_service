package com.engineering_bureau.corporate_service.entity.company;

import com.engineering_bureau.corporate_service.entity.company.CompanyDetails;
import com.engineering_bureau.corporate_service.entity.emploee.Employee;
import com.engineering_bureau.corporate_service.entity.project.Project;
import com.engineering_bureau.corporate_service.enumeration.CompanyType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Employee> employees;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    private CompanyDetails details;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Project> projects;
}
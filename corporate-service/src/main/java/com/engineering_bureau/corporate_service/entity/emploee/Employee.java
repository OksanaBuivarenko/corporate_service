package com.engineering_bureau.corporate_service.entity.emploee;

import com.engineering_bureau.corporate_service.entity.company.Company;
import com.engineering_bureau.corporate_service.entity.project.Project;
import com.engineering_bureau.corporate_service.entity.project.WorkTask;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String patronymic;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "job_title_id")
    private JobTitle jobTitle;

    @ManyToMany
    private List<Project> projects;

    @OneToMany(mappedBy = "employee")
    private List<WorkTask> tasks;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//, mappedBy="employees"
    private ContactDetails contactDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//, mappedBy="employees"
    private PassportDetails passportDetails;
}
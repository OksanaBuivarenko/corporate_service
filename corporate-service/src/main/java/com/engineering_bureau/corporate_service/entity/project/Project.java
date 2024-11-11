package com.engineering_bureau.corporate_service.entity.project;

import com.engineering_bureau.corporate_service.entity.company.Company;
import com.engineering_bureau.corporate_service.entity.emploee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Company client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_id")
    private Employee responsible;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<ProjectStages> stages;


//    @ManyToMany
//    private List<Employee> performersForClient;
//
    //private List<Documents> documents;
}

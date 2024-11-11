package com.engineering_bureau.corporate_service.entity.project;

import com.engineering_bureau.corporate_service.entity.emploee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "work_tasks")
public class WorkTask {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column( nullable = false)
    private String title;

    private String description;

    @Column(name = "start_task", nullable = false)
    private LocalDateTime startTask;

    @Column(name = "end_task", nullable = false)
    private LocalDateTime endTask;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id")
    private ProjectStages projectStage;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "workTask")
    private List<RequiredProduct> requiredProductsList;
}
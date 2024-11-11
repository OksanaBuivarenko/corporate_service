package com.engineering_bureau.corporate_service.entity.project;

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
@Table(name = "project_stages")
public class ProjectStages {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "start_stage", nullable = false)
    private LocalDateTime startStage;

    @Column(name = "end_stage", nullable = false)
    private LocalDateTime endStage;

    @OneToMany(mappedBy = "projectStage", fetch = FetchType.LAZY)
    private List<WorkTask> tasks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
}

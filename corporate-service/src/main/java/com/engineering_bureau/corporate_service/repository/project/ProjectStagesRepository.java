package com.engineering_bureau.corporate_service.repository.project;

import com.engineering_bureau.corporate_service.entity.project.ProjectStages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectStagesRepository extends JpaRepository<ProjectStages, Long> {
    List<ProjectStages> findAllByProject_Id(Long projectId);
}
package com.engineering_bureau.corporate_service.repository.project;

import com.engineering_bureau.corporate_service.entity.project.WorkTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface WorkTaskRepository extends JpaRepository<WorkTask, Long> {
    List<WorkTask> findAllByProjectStage_Id(Long stageId);
}
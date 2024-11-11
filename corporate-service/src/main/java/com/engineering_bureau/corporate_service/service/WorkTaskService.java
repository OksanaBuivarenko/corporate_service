package com.engineering_bureau.corporate_service.service;

import com.engineering_bureau.corporate_service.dto.request.project.WorkTaskRq;
import com.engineering_bureau.corporate_service.dto.response.project.CalculateRequiredProductRs;
import com.engineering_bureau.corporate_service.dto.response.project.CalculateRequiredProductRsWithComponents;
import com.engineering_bureau.corporate_service.dto.response.project.WorkTaskRs;
import com.engineering_bureau.corporate_service.entity.project.WorkTask;

import java.util.List;

public interface WorkTaskService {

    List<WorkTaskRs> getAllWorkTaskRs();

    List<WorkTaskRs> getAllWorkTaskRsByStageId(Long stageId);

    WorkTaskRs getWorkTaskRsById(Long id);

    WorkTask getWorkTaskById(Long id);

    WorkTaskRs createWorkTaskByStageId(Long stageId, WorkTaskRq workTaskRq);

    WorkTaskRs updateWorkTaskById(Long id, WorkTaskRq workTaskRq);

    String deleteWorkTaskById(Long id);

    List<CalculateRequiredProductRs> getCalculateRequiredProductRs(Long id);

    List<CalculateRequiredProductRsWithComponents> getCalculateRequiredProductRsWithComponents(Long id);
}

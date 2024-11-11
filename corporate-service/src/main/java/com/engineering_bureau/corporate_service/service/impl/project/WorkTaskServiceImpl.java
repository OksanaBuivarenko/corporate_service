package com.engineering_bureau.corporate_service.service.impl.project;

import com.engineering_bureau.corporate_service.dto.request.project.WorkTaskRq;
import com.engineering_bureau.corporate_service.dto.response.project.CalculateRequiredProductRs;
import com.engineering_bureau.corporate_service.dto.response.project.CalculateRequiredProductRsWithComponents;
import com.engineering_bureau.corporate_service.dto.response.project.WorkTaskRs;
import com.engineering_bureau.corporate_service.dto.response.storage.CalculateRequiredComponentRs;
import com.engineering_bureau.corporate_service.entity.project.ProjectStages;
import com.engineering_bureau.corporate_service.entity.project.RequiredProduct;
import com.engineering_bureau.corporate_service.entity.project.WorkTask;
import com.engineering_bureau.corporate_service.mapper.project.WorkTaskMapper;
import com.engineering_bureau.corporate_service.repository.project.WorkTaskRepository;
import com.engineering_bureau.corporate_service.service.ProductService;
import com.engineering_bureau.corporate_service.service.ProjectStagesService;
import com.engineering_bureau.corporate_service.service.WorkTaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkTaskServiceImpl implements WorkTaskService {

    private final WorkTaskRepository workTaskRepository;

    private final WorkTaskMapper workTaskMapper;

    private final ProjectStagesService projectStagesService;

    private final ProductService productService;

    @Override
    public WorkTask getWorkTaskById(Long id) {
        return workTaskRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Work's task with id " + id + " not found"));
    }

    @Override
    public List<WorkTaskRs> getAllWorkTaskRs() {
        return workTaskRepository.findAll().stream()
                .map(workTaskMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<WorkTaskRs> getAllWorkTaskRsByStageId(Long stageId) {
        return workTaskRepository.findAllByProjectStage_Id(stageId).stream()
                .map(workTaskMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public WorkTaskRs getWorkTaskRsById(Long id) {
        return workTaskMapper.toDto(getWorkTaskById(id));
    }

    @Override
    public WorkTaskRs createWorkTaskByStageId(Long stageId, WorkTaskRq workTaskRq) {
        ProjectStages projectStages = projectStagesService.getProjectStageById(stageId);
        WorkTask workTask = workTaskMapper.toEntity(workTaskRq, projectStages);
        WorkTask saveWorkTask = workTaskRepository.save(workTask);

        return workTaskMapper.toDto(saveWorkTask);
    }

    @Override
    public WorkTaskRs updateWorkTaskById(Long id, WorkTaskRq workTaskRq) {
        WorkTask workTask = getWorkTaskById(id);
        if (workTaskRq.getTitle()!=null && !workTaskRq.getTitle().trim().isEmpty()) {
            workTask.setTitle(workTaskRq.getTitle());
        }
        if (workTaskRq.getDescription()!=null && !workTaskRq.getDescription().trim().isEmpty()) {
            workTask.setDescription(workTaskRq.getDescription());
        }
        if (workTaskRq.getStartTask()!=null) {
            workTask.setStartTask(workTaskRq.getStartTask());
        }
        if (workTaskRq.getEndTask()!=null) {
            workTask.setEndTask(workTaskRq.getEndTask());
        }
        WorkTask updateWorkTask = workTaskRepository.save(workTask);

        return workTaskMapper.toDto(updateWorkTask);
    }

    @Override
    public String deleteWorkTaskById(Long id) {
        workTaskRepository.delete(getWorkTaskById(id));
        String deleteMessage = "Work's task with id " + id + "delete success";
        log.info(deleteMessage);

        return deleteMessage;
    }

    @Override
    public List<CalculateRequiredProductRs> getCalculateRequiredProductRs(Long id) {
        List<CalculateRequiredProductRs> calculateResult = new ArrayList<>();
        WorkTask task = getWorkTaskById(id);

        for(RequiredProduct requiredProduct: task.getRequiredProductsList()) {

            Integer requiredProductCount = requiredProduct.getRequiredCount();
            Integer doneProductCount = requiredProduct.getProduct().getCountFinishedProducts();
            Integer needToDoProductCount = requiredProductCount - doneProductCount;

            CalculateRequiredProductRs calculateRequiredProductRs = CalculateRequiredProductRs.builder()
                    .requiredProductCount(requiredProductCount)
                    .doneProductCount(doneProductCount)
                    .needToDoProductCount(needToDoProductCount)
                    .build();
            calculateResult.add(calculateRequiredProductRs);
        }
        return calculateResult;
    }

    @Override
    public List<CalculateRequiredProductRsWithComponents> getCalculateRequiredProductRsWithComponents(Long id) {
        List<CalculateRequiredProductRsWithComponents> calculateResult = new ArrayList<>();
        WorkTask task = getWorkTaskById(id);

        for(RequiredProduct requiredProduct: task.getRequiredProductsList()) {

            Integer requiredProductCount = requiredProduct.getRequiredCount();
            Integer doneProductCount = requiredProduct.getProduct().getCountFinishedProducts();
            Integer needToDoProductCount = requiredProductCount - doneProductCount;
            List<CalculateRequiredComponentRs> calculateComponents = productService.getCalculateRequiredComponentRs(
                    requiredProduct.getProduct().getId(), requiredProductCount);

            CalculateRequiredProductRsWithComponents calculateRs = CalculateRequiredProductRsWithComponents.builder()
                    .requiredProductCount(requiredProductCount)
                    .doneProductCount(doneProductCount)
                    .needToDoProductCount(needToDoProductCount)
                    .calculateComponentsForProducts(calculateComponents)
                    .build();
            calculateResult.add(calculateRs);
        }
        return calculateResult;
    }
}
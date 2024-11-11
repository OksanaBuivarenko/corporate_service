package com.engineering_bureau.corporate_service.service.impl.project;

import com.engineering_bureau.corporate_service.dto.request.project.RequiredProductRq;
import com.engineering_bureau.corporate_service.dto.response.project.RequiredProductRs;
import com.engineering_bureau.corporate_service.entity.project.RequiredProduct;
import com.engineering_bureau.corporate_service.entity.project.WorkTask;
import com.engineering_bureau.corporate_service.entity.storage.Product;
import com.engineering_bureau.corporate_service.exception.ObjectAlreadyExistsException;
import com.engineering_bureau.corporate_service.mapper.project.RequiredProductMapper;
import com.engineering_bureau.corporate_service.repository.project.RequiredProductRepository;
import com.engineering_bureau.corporate_service.service.ProductService;
import com.engineering_bureau.corporate_service.service.RequiredProductsService;
import com.engineering_bureau.corporate_service.service.WorkTaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequiredProductsServiceImpl implements RequiredProductsService {

    private final WorkTaskService taskService;

    private final ProductService productService;

    private final RequiredProductRepository requiredProductRepository;

    private final RequiredProductMapper requiredProductMapper;

    @Override
    public RequiredProduct getRequiredProductById(Long id) {
        return requiredProductRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Required product with id " + id + " not found"));
    }

    @Override
    public List<RequiredProductRs> getAllRequiredProductRs() {
        return requiredProductRepository.findAll().stream().map(requiredProductMapper::toDto ).collect(Collectors.toList());
    }

    @Override
    public RequiredProductRs getRequiredProductRsById(Long id) {
        return requiredProductMapper.toDto(getRequiredProductById(id));
    }

    @Override
    public RequiredProductRs createRequiredProductByStageId(Long taskId, RequiredProductRq requiredProductRq) {
        WorkTask workTask = taskService.getWorkTaskById(taskId);
        Product product = productService.getProductByName(requiredProductRq.getProduct());
        if(requiredProductRepository.existsByProductAndWorkTask(product, workTask)) {
            throw new ObjectAlreadyExistsException("Required product with task " + workTask.getTitle() +
                    " and product " + product +"already exists");
        }
        RequiredProduct requiredProduct = new RequiredProduct();
        requiredProduct.setProduct(product);
        requiredProduct.setWorkTask(workTask);
        requiredProduct.setRequiredCount(requiredProductRq.getCount());
        RequiredProduct saveRequiredProduct = requiredProductRepository.save(requiredProduct);
        return requiredProductMapper.toDto(saveRequiredProduct);
    }

    @Override
    public RequiredProductRs updateRequiredProductById(Long id, RequiredProductRq requiredProductRq) {
        RequiredProduct requiredProduct = getRequiredProductById(id);
        requiredProduct.setRequiredCount(requiredProductRq.getCount());
        RequiredProduct updateRequiredProduct = requiredProductRepository.save(requiredProduct);
        return requiredProductMapper.toDto(updateRequiredProduct);
    }

    @Override
    public String deleteRequiredProductById(Long id) {
        requiredProductRepository.delete(getRequiredProductById(id));
        String deleteMessage = "Required product with id " + id + " delete success";
        log.info(deleteMessage);
        return  deleteMessage;
    }
}

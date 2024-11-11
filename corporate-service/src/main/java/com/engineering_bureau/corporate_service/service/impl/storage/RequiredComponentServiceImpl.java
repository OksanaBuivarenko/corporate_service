package com.engineering_bureau.corporate_service.service.impl.storage;

import com.engineering_bureau.corporate_service.dto.request.storage.RequiredComponentRq;
import com.engineering_bureau.corporate_service.dto.response.storage.RequiredComponentRs;
import com.engineering_bureau.corporate_service.entity.storage.Component;
import com.engineering_bureau.corporate_service.entity.storage.Product;
import com.engineering_bureau.corporate_service.entity.storage.RequiredComponent;
import com.engineering_bureau.corporate_service.exception.ObjectAlreadyExistsException;
import com.engineering_bureau.corporate_service.mapper.storage.RequiredComponentMapper;
import com.engineering_bureau.corporate_service.repository.storage.RequiredComponentRepository;
import com.engineering_bureau.corporate_service.service.ComponentService;
import com.engineering_bureau.corporate_service.service.ProductService;
import com.engineering_bureau.corporate_service.service.RequiredComponentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequiredComponentServiceImpl implements RequiredComponentService {

    private final ComponentService componentService;

    private final ProductService productService;

    private final RequiredComponentRepository requiredComponentRepository;

    private final RequiredComponentMapper requiredComponentMapper;

    @Override
    public RequiredComponent getRequiredComponentById(Long id) {
        return requiredComponentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Required component with id " + id + " not found"));
    }

    @Override
    public List<RequiredComponentRs> getAllRequiredComponentRs() {
        return requiredComponentRepository.findAll().stream().map(requiredComponentMapper::toDto ).collect(Collectors.toList());
    }

    @Override
    public RequiredComponentRs getRequiredComponentRsById(Long id) {
        return requiredComponentMapper.toDto(getRequiredComponentById(id));
    }

    @Override
    public RequiredComponentRs createRequiredComponentByProductId(Long productId, RequiredComponentRq requiredProductRq) {
        Component component = componentService.getComponentByName(requiredProductRq.getComponent());
        Product product = productService.getProductById(productId);
        if(requiredComponentRepository.existsByProductAndComponent(product,component)) {
            throw new ObjectAlreadyExistsException("Required component with name " + component.getName() +
                    " and product " + product +"already exists");
        }
        RequiredComponent requiredComponent = new RequiredComponent();
        requiredComponent.setProduct(product);
        requiredComponent.setComponent(component);
        requiredComponent.setRequiredCount(requiredProductRq.getCount());
        RequiredComponent saveRequiredComponent = requiredComponentRepository.save(requiredComponent);
        return requiredComponentMapper.toDto(saveRequiredComponent);
    }

    @Override
    public RequiredComponentRs updateRequiredComponentById(Long id, RequiredComponentRq requiredComponentRq) {
        RequiredComponent requiredComponent = getRequiredComponentById(id);
        requiredComponent.setRequiredCount(requiredComponentRq.getCount());
        RequiredComponent updateRequiredComponent = requiredComponentRepository.save(requiredComponent);
        return requiredComponentMapper.toDto(updateRequiredComponent);
    }

    @Override
    public String deleteRequiredComponentById(Long id) {
        requiredComponentRepository.delete(getRequiredComponentById(id));
        String deleteMessage = "Required component with id " + id + " delete success";
        log.info(deleteMessage);
        return  deleteMessage;
    }
}

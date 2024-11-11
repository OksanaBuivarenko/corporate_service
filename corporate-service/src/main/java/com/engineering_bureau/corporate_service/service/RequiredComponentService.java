package com.engineering_bureau.corporate_service.service;

import com.engineering_bureau.corporate_service.dto.request.storage.RequiredComponentRq;
import com.engineering_bureau.corporate_service.dto.response.storage.RequiredComponentRs;
import com.engineering_bureau.corporate_service.entity.storage.RequiredComponent;

import java.util.List;

public interface RequiredComponentService {

    RequiredComponent getRequiredComponentById(Long id);

    List<RequiredComponentRs> getAllRequiredComponentRs();

    RequiredComponentRs getRequiredComponentRsById(Long id);

    RequiredComponentRs createRequiredComponentByProductId(Long productId, RequiredComponentRq requiredComponentRq);

    RequiredComponentRs updateRequiredComponentById(Long id, RequiredComponentRq requiredComponentRq);

    String deleteRequiredComponentById(Long id);
}

package com.engineering_bureau.corporate_service.service;

import com.engineering_bureau.corporate_service.dto.request.project.RequiredProductRq;
import com.engineering_bureau.corporate_service.dto.response.project.RequiredProductRs;
import com.engineering_bureau.corporate_service.entity.project.RequiredProduct;

import java.util.List;

public interface RequiredProductsService {

    RequiredProduct getRequiredProductById(Long id);

    List<RequiredProductRs> getAllRequiredProductRs();

    RequiredProductRs getRequiredProductRsById(Long id);

    RequiredProductRs createRequiredProductByStageId(Long taskId, RequiredProductRq requiredProductRq);

    RequiredProductRs updateRequiredProductById(Long id, RequiredProductRq requiredProductRq);

    String deleteRequiredProductById(Long id);
}

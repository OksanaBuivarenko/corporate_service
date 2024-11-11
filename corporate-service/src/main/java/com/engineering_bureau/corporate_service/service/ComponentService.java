package com.engineering_bureau.corporate_service.service;

import com.engineering_bureau.corporate_service.dto.PageRs;
import com.engineering_bureau.corporate_service.dto.request.storage.ComponentRq;
import com.engineering_bureau.corporate_service.dto.response.storage.ComponentRs;
import com.engineering_bureau.corporate_service.entity.storage.Component;

import java.util.List;

public interface ComponentService {

    Component getComponentById(Long id);

    PageRs<List<ComponentRs>> getAllComponentRs(Integer offset, Integer limit);

    ComponentRs getComponentRsById(Long id);

    ComponentRs createComponent(ComponentRq componentRq);

    ComponentRs updateCompanyById(Long id, ComponentRq componentRq);

    String deleteComponentById(Long id);

    Component getComponentByName(String component);
}

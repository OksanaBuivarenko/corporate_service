package com.engineering_bureau.corporate_service.repository.storage;

import com.engineering_bureau.corporate_service.entity.storage.Component;
import com.engineering_bureau.corporate_service.entity.storage.Product;
import com.engineering_bureau.corporate_service.entity.storage.RequiredComponent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequiredComponentRepository extends JpaRepository<RequiredComponent, Long> {
    boolean existsByProductAndComponent(Product product, Component component);
}

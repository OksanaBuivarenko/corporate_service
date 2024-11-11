package com.engineering_bureau.corporate_service.repository.project;

import com.engineering_bureau.corporate_service.entity.project.RequiredProduct;
import com.engineering_bureau.corporate_service.entity.project.WorkTask;
import com.engineering_bureau.corporate_service.entity.storage.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequiredProductRepository extends JpaRepository<RequiredProduct, Long> {
    boolean existsByProductAndWorkTask(Product product, WorkTask workTask);
}

package com.engineering_bureau.corporate_service.repository.storage;

import com.engineering_bureau.corporate_service.entity.storage.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EquipmentRepository extends JpaRepository<Equipment, Long>, JpaSpecificationExecutor<Equipment> {
    boolean existsByName(String name);
}

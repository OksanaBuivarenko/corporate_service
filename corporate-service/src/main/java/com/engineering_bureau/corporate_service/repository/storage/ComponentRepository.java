package com.engineering_bureau.corporate_service.repository.storage;

import com.engineering_bureau.corporate_service.entity.storage.Component;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComponentRepository extends JpaRepository<Component, Long> {
    boolean existsByName(String name);

    Optional<Component> findByName(String name);
}

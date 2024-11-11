package com.engineering_bureau.corporate_service.repository.employee;

import com.engineering_bureau.corporate_service.entity.emploee.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobTitleRepository extends JpaRepository<JobTitle, Long> {
    Optional<JobTitle> findByTitle(String jobTitle);

    boolean existsByTitle(String title);
}

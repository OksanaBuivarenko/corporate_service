package com.engineering_bureau.corporate_service.entity.project;

import com.engineering_bureau.corporate_service.entity.storage.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "required_products")
public class RequiredProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "required_count")
    private Integer requiredCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_task_id")
    private WorkTask workTask;
}
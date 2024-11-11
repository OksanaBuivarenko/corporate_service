package com.engineering_bureau.corporate_service.entity.storage;

import com.engineering_bureau.corporate_service.entity.storage.RequiredComponent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String description;

    @Column(name = "count_finished_products")
    private Integer countFinishedProducts;

    @Column(name = "storage_place")
    private String storagePlace;

    @ManyToMany
    private List<RequiredComponent> componentsList;
}
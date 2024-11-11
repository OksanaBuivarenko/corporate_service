package com.engineering_bureau.corporate_service.service.impl.storage;

import com.engineering_bureau.corporate_service.dto.PageRs;
import com.engineering_bureau.corporate_service.dto.response.storage.CalculateRequiredComponentRs;
import com.engineering_bureau.corporate_service.entity.storage.Product_;
import com.engineering_bureau.corporate_service.entity.storage.RequiredComponent;
import com.engineering_bureau.corporate_service.service.ProductService;
import com.engineering_bureau.corporate_service.dto.request.storage.ProductRq;
import com.engineering_bureau.corporate_service.dto.request.storage.ProductSearchRq;
import com.engineering_bureau.corporate_service.dto.response.storage.ProductRs;
import com.engineering_bureau.corporate_service.entity.storage.Product;
import com.engineering_bureau.corporate_service.exception.ObjectAlreadyExistsException;
import com.engineering_bureau.corporate_service.mapper.storage.ProductMapper;
import com.engineering_bureau.corporate_service.repository.specification.Specs;
import com.engineering_bureau.corporate_service.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Product with id " + id + " not found"));
    }

    @Override
    public PageRs<List<ProductRs>> getAllProductRs(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return PageRs.<List<ProductRs>>builder()
                .offset(offset)
                .limit(limit)
                .data(productRepository.findAll(nextPage).stream().map(productMapper::toDto ).collect(Collectors.toList()))
                .build();
    }

    @Override
    public ProductRs getProductRsById(Long id) {
        return productMapper.toDto(getProductById(id));
    }

    @Override
    public ProductRs createProduct(ProductRq productRq) {
        if(productRepository.existsByName(productRq.getName())) {
            throw new ObjectAlreadyExistsException("Product with name " + productRq.getName() + "already exists");
        }
        Product product = productMapper.toEntity(productRq);
        Product saveProduct = productRepository.save(product);
        return productMapper.toDto(saveProduct);
    }

    @Override
    public ProductRs updateProductById(Long id, ProductRq productRq) {
        Product product = getProductById(id);
        if (productRq.getName()!=null && !productRq.getName().trim().isEmpty()) {
            product.setName(productRq.getName());
        }
        if (productRq.getDescription()!=null && !productRq.getDescription().trim().isEmpty()) {
            product.setDescription(productRq.getDescription());
        }
        if (productRq.getCountFinishedProducts()!=null) {
            product.setCountFinishedProducts(productRq.getCountFinishedProducts());
        }
        if (productRq.getStoragePlace()!=null && !productRq.getStoragePlace().trim().isEmpty()) {
            product.setStoragePlace(productRq.getStoragePlace());
        }
        Product updateProduct = productRepository.save(product);
        return productMapper.toDto(updateProduct);
    }

    @Override
    public String deleteProductById(Long id) {
        productRepository.delete(getProductById(id));
        String deleteMessage = "Product with id " + id + "delete success";
        log.info(deleteMessage);
        return  deleteMessage;
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name).orElseThrow(() ->
                new EntityNotFoundException("Product with name " + name + " not found"));
    }

    @Override
    public List<CalculateRequiredComponentRs> getCalculateRequiredComponentRs(Long id, Integer productCount) {
        List<CalculateRequiredComponentRs> calculateResult = new ArrayList<>();
        Product product = getProductById(id);

        for(RequiredComponent requiredComponent: product.getComponentsList()) {

            Integer requiredComponentCount = requiredComponent.getRequiredCount() * productCount;
            Integer inStockComponentCount = requiredComponent.getComponent().getCount();
            Integer outOfStockComponentCount = requiredComponentCount - inStockComponentCount;

            CalculateRequiredComponentRs calculateRequiredComponentRs = CalculateRequiredComponentRs.builder()
                    .requiredComponentCount(requiredComponentCount)
                    .inStockComponentCount(inStockComponentCount)
                    .outOfStockComponentCount(outOfStockComponentCount)
                    .build();
            calculateResult.add(calculateRequiredComponentRs);
        }
        return calculateResult;
    }
    @Override
    public PageRs<List<ProductRs>> searchProductRsByQuery(ProductSearchRq searchRq, Integer offset, Integer limit) {
        List<ProductRs> resultList = productRepository.findAll(
                Specification.where(Specs.eq(Product_.id, searchRq.getId()))
                        .and(Specs.eq(Product_.name, searchRq.getName()))
                        .and(Specs.eq(Product_.storagePlace, searchRq.getStoragePlace())),
                PageRequest.of(offset, limit))
                .stream().map(productMapper::toDto).collect(Collectors.toList());

        return PageRs.<List<ProductRs>>builder()
                .offset(offset)
                .limit(limit)
                .data(resultList)
                .build();
    }
}
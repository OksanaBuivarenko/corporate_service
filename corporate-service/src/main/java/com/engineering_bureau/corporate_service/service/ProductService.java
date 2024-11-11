package com.engineering_bureau.corporate_service.service;

import com.engineering_bureau.corporate_service.dto.PageRs;
import com.engineering_bureau.corporate_service.dto.request.storage.ProductRq;
import com.engineering_bureau.corporate_service.dto.request.storage.ProductSearchRq;
import com.engineering_bureau.corporate_service.dto.response.storage.CalculateRequiredComponentRs;
import com.engineering_bureau.corporate_service.dto.response.storage.ProductRs;
import com.engineering_bureau.corporate_service.entity.storage.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);

    PageRs<List<ProductRs>> getAllProductRs(Integer offset, Integer limit);

    ProductRs getProductRsById(Long id);

    ProductRs createProduct(ProductRq productRq);

    ProductRs updateProductById(Long id, ProductRq productRq);

    String deleteProductById(Long id);

    PageRs<List<ProductRs>> searchProductRsByQuery(ProductSearchRq searchRq, Integer offset, Integer limit);

    Product getProductByName(String name);


    List<CalculateRequiredComponentRs> getCalculateRequiredComponentRs(Long id, Integer productCount);
}

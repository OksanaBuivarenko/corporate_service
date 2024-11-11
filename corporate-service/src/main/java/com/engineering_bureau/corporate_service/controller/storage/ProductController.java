package com.engineering_bureau.corporate_service.controller.storage;

import com.engineering_bureau.corporate_service.dto.PageRs;
import com.engineering_bureau.corporate_service.dto.request.Marker;
import com.engineering_bureau.corporate_service.dto.request.PageSizeRq;
import com.engineering_bureau.corporate_service.dto.request.storage.ProductRq;
import com.engineering_bureau.corporate_service.dto.request.storage.ProductSearchRq;
import com.engineering_bureau.corporate_service.dto.response.project.CalculateRequiredProductRs;
import com.engineering_bureau.corporate_service.dto.response.storage.CalculateRequiredComponentRs;
import com.engineering_bureau.corporate_service.dto.response.storage.ProductRs;
import com.engineering_bureau.corporate_service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Operation(description = "Getting all products")
    @GetMapping()
    public PageRs<List<ProductRs>> getAllProductRs(@ParameterObject @Valid PageSizeRq req) {
        return productService.getAllProductRs(req.getOffset(), req.getLimit());
    }

    @Operation(description = "Getting product by id")
    @GetMapping("/{id}")
    public ProductRs getProductRsById(@PathVariable Long id) {
        return productService.getProductRsById(id);
    }

    @Operation(description = "Calculate in stock component by product id")
    @GetMapping("/calculate/{id}")
    public List<CalculateRequiredComponentRs> getCalculateRequiredComponentRs(@PathVariable Long id) {
        return productService.getCalculateRequiredComponentRs(id, 1);
    }

    @Validated({Marker.OnCreate.class})
    @Operation(description = "Create product")
    @PostMapping()
    public ProductRs createProduct(@RequestBody @Valid ProductRq productRq) {
        return productService.createProduct(productRq);
    }

    @Validated({Marker.OnUpdate.class})
    @Operation(description = "Update product by id")
    @PutMapping("/{id}")
    public ProductRs updateProductById(@PathVariable Long id, @RequestBody @Valid ProductRq productRq) {
        return productService.updateProductById(id, productRq);
    }

    @Operation(description = "Delete product")
    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }

    @Operation(description = "Searching product by query")
    @GetMapping(value = "/search")
    public PageRs<List<ProductRs>>  searchProductRsByQuery(@ParameterObject @Valid ProductSearchRq searchRq,
                                                           @ParameterObject @Valid PageSizeRq req) {
        return productService.searchProductRsByQuery(searchRq, req.getOffset(), req.getLimit());
    }
}

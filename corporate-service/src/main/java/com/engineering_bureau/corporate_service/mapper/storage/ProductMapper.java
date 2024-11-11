package com.engineering_bureau.corporate_service.mapper.storage;

import com.engineering_bureau.corporate_service.dto.request.storage.ProductRq;
import com.engineering_bureau.corporate_service.dto.response.storage.ProductRs;
import com.engineering_bureau.corporate_service.entity.storage.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    ProductRs toDto(Product product);

    Product toEntity(ProductRq productRq);
}

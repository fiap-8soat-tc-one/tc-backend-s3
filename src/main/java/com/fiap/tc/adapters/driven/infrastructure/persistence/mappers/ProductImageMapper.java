package com.fiap.tc.adapters.driven.infrastructure.persistence.mappers;

import com.fiap.tc.adapters.driven.infrastructure.persistence.entity.ProductImageEntity;
import com.fiap.tc.adapters.driven.infrastructure.persistence.mappers.base.MapperEntity;
import com.fiap.tc.core.domain.model.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductImageMapper extends MapperEntity<ProductImageEntity, ProductImage> {

    @Override
    @Mapping(target = "id", source = "uuid")
    ProductImage fromEntity(ProductImageEntity productImageEntity);

    @Override
    @Mapping(target = "id", ignore = true)
    ProductImageEntity toEntity(ProductImage productImage);

}



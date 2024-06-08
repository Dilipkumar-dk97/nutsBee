package com.application.nutsBee.mapper;

import org.mapstruct.Mapper;

import com.application.nutsBee.Dto.ProductsDto;
import com.application.nutsBee.Entity.Products;

@Mapper(componentModel = "spring")
public interface ProductsMapper {
	Products ProductsDtoTOProducts(ProductsDto product);
}

package com.application.nutsBee.service;

import java.util.List;

import com.application.nutsBee.Dto.ProductsDto;
import com.application.nutsBee.Entity.Products;

public interface ProductService{
	
	Products createProduct(ProductsDto product);

	List<Products> getAllProduct();

	Products getProductBy(Long productId);
	
}

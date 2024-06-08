package com.application.nutsBee.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.nutsBee.Dto.ProductsDto;
import com.application.nutsBee.Entity.Products;
import com.application.nutsBee.mapper.ProductsMapper;
import com.application.nutsBee.repository.ProductRepository;
import com.application.nutsBee.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductsMapper productsMapper;

	@Override
	public Products createProduct(ProductsDto product) {
		Products products = productsMapper.ProductsDtoTOProducts(product);
		products = productRepository.save(products);
		return products;
	}

	@Override
	public List<Products> getAllProduct() {
		List<Products> productList = productRepository.findAll();
		return productList;
	}

	@Override
	public Products getProductBy(Long productId) {
		Optional<Products> products = productRepository.findById(productId);
		return products.get();
	}
}

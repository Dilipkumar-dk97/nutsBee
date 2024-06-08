package com.application.nutsBee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.nutsBee.Dto.ProductsDto;
import com.application.nutsBee.Entity.Products;
import com.application.nutsBee.Entity.Role;
import com.application.nutsBee.service.ProductService;

@RestController
@RequestMapping("/nutsBee")
public class ProductsController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/products")
	public ResponseEntity<Products> createProducts(@RequestBody ProductsDto product) {
		Products products = productService.createProduct(product);
		if (products == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Products>> getProducts() {
		List<Products> products = productService.getAllProduct();
		if (products == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(products);
	}	
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<Products> getProducts(@PathVariable Long productId) {
		Products products = productService.getProductBy(productId);
		if (products == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(products);
	}
}

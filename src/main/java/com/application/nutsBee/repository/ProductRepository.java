package com.application.nutsBee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.nutsBee.Entity.Products;

public interface ProductRepository extends JpaRepository<Products, Long >{

}

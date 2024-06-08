package com.application.nutsBee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.nutsBee.Entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}

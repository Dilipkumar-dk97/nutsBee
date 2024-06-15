package com.application.nutsBee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.nutsBee.Entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
	
	@Query("SELECT DISTINCT cart FROM Cart cart WHERE cart.userId = :userId")
	List<Cart> findCartByUserId(Long userId);
}

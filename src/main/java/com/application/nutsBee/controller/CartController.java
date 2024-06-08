package com.application.nutsBee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.nutsBee.Entity.Cart;
import com.application.nutsBee.service.CartService;

@RestController
@RequestMapping("/nutsBee")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/cart")
	public ResponseEntity<Cart> addToCart(@RequestParam Long userId, @RequestParam Long productId) {
		Cart cart = cartService.addToCart(userId,productId);
		if (cart == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cart);
	}
}

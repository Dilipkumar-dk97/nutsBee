package com.application.nutsBee.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/cart")
	public ResponseEntity<List<Cart>> getCartItems(@RequestParam Long userId) {
		List<Cart> cart = cartService.getCartItems(userId);
		if (cart == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cart);
	}
	
	@DeleteMapping("/cart/{cartId}")
	public ResponseEntity<String> deleteCartItem(@PathVariable Long cartId) {
		cartService.deleteCartItem(cartId);
		return ResponseEntity.ok("Deleted SuccessFully");
	}
	
	@PatchMapping("/cart/{cartId}")
	public ResponseEntity<Cart> patchCartItem(@PathVariable Long cartId, @RequestBody Map<String,String> data) {
		Cart cart = cartService.patchCartItem(cartId,data);
		return ResponseEntity.ok(cart);
	}
}

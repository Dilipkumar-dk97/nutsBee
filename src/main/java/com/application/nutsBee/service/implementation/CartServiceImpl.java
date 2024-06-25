package com.application.nutsBee.service.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.nutsBee.Entity.Cart;
import com.application.nutsBee.Entity.Products;
import com.application.nutsBee.Entity.User;
import com.application.nutsBee.repository.CartRepository;
import com.application.nutsBee.service.CartService;
import com.application.nutsBee.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductsServiceImpl productService;
	
	@Override
	public Cart addToCart(Long userId, Long productId) {
		Cart carts = new Cart();
		Products product = productService.getProductBy(productId);
		List<Cart> cartItems = getCartItems(userId);
		List<Cart> existingCart = cartItems.stream()
				.filter(cart -> StringUtils.equals(cart.getItemId(), productId.toString()))
				.collect(Collectors.toList());
		if(existingCart.isEmpty()) {
			carts.setItemId(product.getId().toString());
			carts.setItemName(product.getProductName());
			carts.setPrice(product.getPrice());
			carts.setQuantity(1);
			carts.setUserId(userId);
			return cartRepository.save(carts);
		}else {
			Cart cart = existingCart.get(0);
			Map<String, String> data = new HashMap<>();
			Integer quantity = cart.getQuantity()+1;
			data.put("quantity", quantity.toString());
			return patchCartItem(cart.getId(), data);
		}
	}

	@Override
	public List<Cart> getCartItems(Long userId) {
		return cartRepository.findCartByUserId(userId);
	}

	@Override
	public void deleteCartItem(Long cartId) {
		cartRepository.deleteById(cartId);
	}

	@Override
	public Cart patchCartItem(Long cartId,  Map<String, String> data) {
		Cart cart = cartRepository.findById(cartId).get();
		if(data.containsKey("quantity")) {
			cart.setQuantity(Integer.parseInt(data.get("quantity")));
			cartRepository.save(cart);
			return cart;
		}
		return cart;
	}

}

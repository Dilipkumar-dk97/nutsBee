package com.application.nutsBee.service.implementation;

import java.util.List;

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
		carts.setItemId(product.getProductId());
		carts.setItemName(product.getProductName());
		carts.setPrice(product.getPrice());
		carts.setQuantity(1);
		carts.setUserId(userId);
		return cartRepository.save(carts);
	}

	@Override
	public List<Cart> getCartItems(Long userId) {
		return cartRepository.findCartByUserId(userId);
	}

	@Override
	public void deleteCartItem(Long cartId) {
		cartRepository.deleteById(cartId);
	}

}

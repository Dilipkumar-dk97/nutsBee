package com.application.nutsBee.service;

import java.util.List;
import java.util.Map;

import com.application.nutsBee.Entity.Cart;

public interface CartService {

	Cart addToCart(Long userId, Long productId);

	List<Cart> getCartItems(Long userId);

    void  deleteCartItem(Long cartId);

	Cart patchCartItem(Long cartId, Map<String, String> data);

}
